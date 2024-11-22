package com.arthurlamberti.cdb.application.wallet.create;

public record CreateWalletCommand(
        Double amount,
        String customerId
) {
    public static CreateWalletCommand with(
            final Double amount,
            final String customerId
    ) {
        return new CreateWalletCommand(amount, customerId);
    }
}
