package com.arthurlamberti.cdb.application.wallet.paper.buy;

import com.arthurlamberti.cdb.domain.adapters.feing.CustomerExternal;
import com.arthurlamberti.cdb.domain.adapters.models.CustomerExternalDomain;
import com.arthurlamberti.cdb.domain.paper.Paper;
import com.arthurlamberti.cdb.domain.paper.PaperGateway;
import com.arthurlamberti.cdb.domain.paper.PaperID;
import com.arthurlamberti.cdb.domain.wallet.WalletGateway;

import java.util.Optional;

public class DefaultBuyPaperWalletUseCase extends BuyPaperWalletUseCase {

    private final WalletGateway walletGateway;
    private final PaperGateway paperGateway;
    private final CustomerExternal customerExternal;

    public DefaultBuyPaperWalletUseCase(final WalletGateway walletGateway, PaperGateway paperGateway, CustomerExternal customerExternal) {
        this.walletGateway = walletGateway;
        this.paperGateway = paperGateway;
        this.customerExternal = customerExternal;
    }

    @Override
    public void execute(final BuyPaperCommand aCommand) {
        //get paper

        Optional<Paper> optPaper = paperGateway.findById(PaperID.from(aCommand.paperId()));
        CustomerExternalDomain customerExternalDomain = customerExternal.getCustomer(aCommand.customerId());
        //verify if customer contains balance
        final var totalValue = customerExternalDomain.balance() * optPaper.get().getValue();
        //if true, persist and send message on kafka

    }
}
