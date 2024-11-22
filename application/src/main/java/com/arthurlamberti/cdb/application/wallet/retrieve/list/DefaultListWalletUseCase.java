package com.arthurlamberti.cdb.application.wallet.retrieve.list;

import com.arthurlamberti.cdb.domain.wallet.WalletGateway;

import java.util.List;

public class DefaultListWalletUseCase extends ListWalletUseCase {

    private WalletGateway walletGateway;

    public DefaultListWalletUseCase(
            final WalletGateway walletGateway
    ) {
        this.walletGateway = walletGateway;
    }

    @Override
    public List<ListWalletOutput> execute() {
        return this.walletGateway.findAll()
                .stream()
                .map(ListWalletOutput::from)
                .toList();
    }
}
