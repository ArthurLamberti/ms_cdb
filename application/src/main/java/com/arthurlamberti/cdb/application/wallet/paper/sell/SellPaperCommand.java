package com.arthurlamberti.cdb.application.wallet.paper.sell;

public record SellPaperCommand(
        String customerId,
        String paperId,
        Integer amount
) {
    public static SellPaperCommand with(String customerId, String paperId, Integer amount) {
        return new SellPaperCommand(customerId, paperId, amount);
    }
}
