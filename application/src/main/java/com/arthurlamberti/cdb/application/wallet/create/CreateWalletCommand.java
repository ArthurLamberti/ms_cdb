package com.arthurlamberti.cdb.application.wallet.create;

public record CreateWalletCommand(
        Integer amount,
        String customerId,
        String paperId
) {
    public static CreateWalletCommand with(
            final Integer amount,
            final String customerId,
            final String paperId
    ) {
        return new CreateWalletCommand(amount, customerId, paperId);
    }
}
