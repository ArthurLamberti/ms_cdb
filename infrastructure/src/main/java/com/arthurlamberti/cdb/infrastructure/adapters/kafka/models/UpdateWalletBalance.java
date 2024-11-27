package com.arthurlamberti.cdb.infrastructure.adapters.kafka.models;

import com.arthurlamberti.cdb.domain.enums.TransactionType;

public record UpdateWalletBalance (
        String customerId,
        Double balance,
        TransactionType type,
        String transactionId
) {
    public static UpdateWalletBalance with(
            final String customerId,
            final Double balance,
            TransactionType type,
            final String transactionId) {
        return new UpdateWalletBalance(customerId, balance, type, transactionId);
    }
}
