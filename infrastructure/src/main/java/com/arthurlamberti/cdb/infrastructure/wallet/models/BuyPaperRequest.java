package com.arthurlamberti.cdb.infrastructure.wallet.models;

public record BuyPaperRequest(
        String customerId,
        String paperId,
        Integer amount
) {
}
