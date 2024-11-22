package com.arthurlamberti.cdb.application.wallet.retrieve.get;

import com.arthurlamberti.cdb.domain.exceptions.NotFoundException;
import com.arthurlamberti.cdb.domain.wallet.Wallet;
import com.arthurlamberti.cdb.domain.wallet.WalletGateway;
import com.arthurlamberti.cdb.domain.wallet.WalletID;

public class DefaultGetWalletUseCase extends GetWalletUseCase {

    private final WalletGateway walletGateway;

    public DefaultGetWalletUseCase(
            final WalletGateway walletGateway
    ) {
        this.walletGateway = walletGateway;
    }


    @Override
    public GetWalletOutput execute(String input) {
        return this.walletGateway.findById(WalletID.from(input))
                .map(GetWalletOutput::from)
                .orElseThrow(() -> NotFoundException.with(Wallet.class, WalletID.from(input)));
    }
}
