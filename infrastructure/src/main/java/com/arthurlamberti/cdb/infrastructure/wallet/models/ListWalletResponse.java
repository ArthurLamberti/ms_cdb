package com.arthurlamberti.cdb.infrastructure.wallet.models;

public record ListWalletResponse(
        String id,
        Double amount,
        String customerId
) {
}
