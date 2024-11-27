package com.arthurlamberti.cdb.infrastructure.transaction_paper.models;


import com.arthurlamberti.cdb.domain.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateWalletResponse(
        @JsonProperty("status") String status,
        @JsonProperty("transaction_id") String transactionId,
        @JsonProperty("type") TransactionType type
) {
    public static UpdateWalletResponse from(String status, String transactionId, TransactionType type) {
        return new UpdateWalletResponse(status, transactionId, type);
    }
}
