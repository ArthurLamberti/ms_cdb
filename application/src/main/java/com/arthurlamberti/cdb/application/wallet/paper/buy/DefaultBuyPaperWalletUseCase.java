package com.arthurlamberti.cdb.application.wallet.paper.buy;

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

public class DefaultBuyPaperWalletUseCase extends BuyPaperWalletUseCase {

    private final WalletGateway walletGateway;
    private final PaperGateway paperGateway;
    private final CustomerExternal customerExternal;
    private final WalletProducerKafka walletProducerKafka;

    public DefaultBuyPaperWalletUseCase(final WalletGateway walletGateway, PaperGateway paperGateway, CustomerExternal customerExternal, WalletProducerKafka walletProducerKafka) {
        this.walletGateway = walletGateway;
        this.paperGateway = paperGateway;
        this.customerExternal = customerExternal;
        this.walletProducerKafka = walletProducerKafka;
    }

    @Override
    public void execute(final BuyPaperCommand aCommand) {
        //get paper

        Optional<Paper> optPaper = paperGateway.findById(PaperID.from(aCommand.paperId()));
        if(optPaper.isEmpty()) {
            throw DomainException.with(new Error("paper %s does not exists".formatted(aCommand.paperId())));
        }
        CustomerExternalDomain customerExternalDomain = customerExternal.getCustomer(aCommand.customerId());
        //verify if customer contains balance
        final var totalValue = optPaper.get().getValue() * aCommand.amount();
        //if true, persist and send message on kafka
        if (customerExternalDomain.balance() < totalValue) {
            throw DomainException.with(new Error("balance should be greater or equals value"));
        }
        //verify if exists a wallet with customer id and paper
        final var optWallet = walletGateway.findByCustomerIdAndPaperId(aCommand.customerId(),aCommand.paperId());
        final var wallet = optWallet.orElseGet(
                () -> Wallet.newWallet(0, aCommand.customerId(), aCommand.paperId())
        );
        final var updatedWallet = wallet.incrementAmount(aCommand.amount());
        walletGateway.update(updatedWallet);
        walletProducerKafka.updateBalance(updatedWallet, totalValue * -1);
    }
}
