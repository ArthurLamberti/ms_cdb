package com.arthurlamberti.cdb.infrastructure.wallet.models;

public record GetWalletResponse (
        String id,
        Double amount,
        String customerId
){
}
