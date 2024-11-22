package com.arthurlamberti.cdb.application.wallet.paper.buy;

public record BuyPaperCommand (
        String customerId,
        String paperId,
        Integer amount
) {
    public BuyPaperCommand with(String customerId, String paperId, Integer amount) {
        return new BuyPaperCommand(customerId, paperId, amount);
    }
}
