package com.arthurlamberti.cdb.infrastructure.wallet.models;

public record GetWalletResponse (
        String id,
        Integer amount,
        String customerId,
        String paperId
){
}
