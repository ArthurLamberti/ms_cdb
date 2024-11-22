package com.arthurlamberti.cdb.application.wallet.create;

import com.arthurlamberti.cdb.application.paper.create.DefaultCreatePaperUseCase;
import com.arthurlamberti.cdb.domain.exceptions.NotificationException;
import com.arthurlamberti.cdb.domain.validation.handler.Notification;
import com.arthurlamberti.cdb.domain.wallet.Wallet;
import com.arthurlamberti.cdb.domain.wallet.WalletGateway;

public class DefaultCreateWalletUseCase extends CreateWalletUseCase {

    private final WalletGateway walletGateway;

    public DefaultCreateWalletUseCase(
            final WalletGateway walletGateway
    ) {
        this.walletGateway = walletGateway;
    }

    @Override
    public CreateWalletOutput execute(CreateWalletCommand input) {
        final var notification = Notification.create();
        final var aWallet = notification.validate(
                () -> Wallet.newWallet(input.amount(), input.customerId(), input.paperId())
        );
        if (notification.hasError()) {
            throw new NotificationException("Could not create aggregate Wallet", notification);
        }
        return CreateWalletOutput.from(walletGateway.create(aWallet));
    }
}
