package com.arthurlamberti.cdb.application.wallet.paper.sell;

import com.arthurlamberti.cdb.domain.adapters.feing.CustomerExternal;
import com.arthurlamberti.cdb.domain.adapters.kafka.WalletProducerKafka;
import com.arthurlamberti.cdb.domain.adapters.models.CustomerExternalDomain;
import com.arthurlamberti.cdb.domain.exceptions.DomainException;
import com.arthurlamberti.cdb.domain.paper.Paper;
import com.arthurlamberti.cdb.domain.paper.PaperGateway;
import com.arthurlamberti.cdb.domain.paper.PaperID;
import com.arthurlamberti.cdb.domain.validation.Error;
import com.arthurlamberti.cdb.domain.wallet.Wallet;
import com.arthurlamberti.cdb.domain.wallet.WalletGateway;

import java.util.Optional;

public class DefaultSellPaperWalletUseCase extends SellPaperWalletUseCase {

    private final WalletGateway walletGateway;
    private final PaperGateway paperGateway;
    private final CustomerExternal customerExternal;
    private final WalletProducerKafka walletProducerKafka;

    public DefaultSellPaperWalletUseCase(final WalletGateway walletGateway, PaperGateway paperGateway, CustomerExternal customerExternal, WalletProducerKafka walletProducerKafka) {
        this.walletGateway = walletGateway;
        this.paperGateway = paperGateway;
        this.customerExternal = customerExternal;
        this.walletProducerKafka = walletProducerKafka;
    }

    @Override
    public void execute(final SellPaperCommand aCommand) {
        Optional<Paper> optPaper = paperGateway.findById(PaperID.from(aCommand.paperId()));
        if(optPaper.isEmpty()) {
            throw DomainException.with(new com.arthurlamberti.cdb.domain.validation.Error("paper %s does not exists".formatted(aCommand.paperId())));
        }
        final var paper = optPaper.get();
        //verify if exists a wallet with customer id and paper
        final var optWallet = walletGateway.findByCustomerIdAndPaperId(aCommand.customerId(),aCommand.paperId());
        final var wallet = optWallet.orElseGet(
                () -> Wallet.newWallet(0, aCommand.customerId(), aCommand.paperId())
        );
        if (wallet.getAmount() < aCommand.amount()) {
            throw DomainException.with(new Error("Customer does not contains %d papers".formatted(aCommand.amount())));
        }
        final var totalValue = paper.getValue() + aCommand.amount();
        final var updatedWallet = wallet.decrementAmount(aCommand.amount());
        walletGateway.update(updatedWallet);
        walletProducerKafka.updateBalance(updatedWallet, totalValue);
    }
}
