package com.arthurlamberti.cdb.application.wallet.create;

import com.arthurlamberti.cdb.application.paper.create.DefaultCreatePaperUseCase;
import com.arthurlamberti.cdb.domain.exceptions.DomainException;
import com.arthurlamberti.cdb.domain.exceptions.NotificationException;
import com.arthurlamberti.cdb.domain.paper.Paper;
import com.arthurlamberti.cdb.domain.paper.PaperGateway;
import com.arthurlamberti.cdb.domain.paper.PaperID;
import com.arthurlamberti.cdb.domain.validation.Error;
import com.arthurlamberti.cdb.domain.validation.handler.Notification;
import com.arthurlamberti.cdb.domain.wallet.Wallet;
import com.arthurlamberti.cdb.domain.wallet.WalletGateway;

import java.util.Optional;

public class DefaultCreateWalletUseCase extends CreateWalletUseCase {

    private final WalletGateway walletGateway;
    private final PaperGateway paperGateway;

    public DefaultCreateWalletUseCase(
            final WalletGateway walletGateway,
            final PaperGateway paperGateway
    ) {
        this.walletGateway = walletGateway;
        this.paperGateway = paperGateway;
    }

    @Override
    public CreateWalletOutput execute(CreateWalletCommand aCommand) {
        final var notification = Notification.create();

        Optional<Paper> optPaper = paperGateway.findById(PaperID.from(aCommand.paperId()));
        if(optPaper.isEmpty()) {
            throw DomainException.with(new Error("paper %s does not exists".formatted(aCommand.paperId())));
        }

        final var aWallet = notification.validate(
                () -> Wallet.newWallet(aCommand.amount(), aCommand.customerId(), optPaper.get())
        );
        if (notification.hasError()) {
            throw new NotificationException("Could not create aggregate Wallet", notification);
        }
        return CreateWalletOutput.from(walletGateway.create(aWallet));
    }
}
