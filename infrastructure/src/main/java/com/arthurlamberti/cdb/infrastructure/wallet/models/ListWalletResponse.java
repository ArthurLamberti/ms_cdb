package com.arthurlamberti.cdb.infrastructure.wallet.models;

public record ListWalletResponse(
        String id,
        Integer amount,
        String customerId
) {
}
