package com.arthurlamberti.cdb.application.wallet.create;

import com.arthurlamberti.cdb.domain.wallet.Wallet;

public record CreateWalletOutput(String id) {
    public static CreateWalletOutput from(Wallet aWallet) {
        return new CreateWalletOutput(aWallet.getId().getValue());
    }
}
