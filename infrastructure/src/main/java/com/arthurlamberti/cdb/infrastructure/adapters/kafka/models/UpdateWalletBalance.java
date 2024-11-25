package com.arthurlamberti.cdb.infrastructure.adapters.kafka.models;

public record UpdateWalletBalance (
        String customerId,
        Double balance
) {
    public static UpdateWalletBalance with(
            final String customerId,
            final Double balance
    ) {
        return new UpdateWalletBalance(customerId, balance);
    }
}
