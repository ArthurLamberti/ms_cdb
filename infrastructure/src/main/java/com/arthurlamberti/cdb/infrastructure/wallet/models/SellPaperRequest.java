package com.arthurlamberti.cdb.infrastructure.wallet.models;

public record SellPaperRequest(
        String customerId,
        String paperId,
        Integer amount
) {
}
