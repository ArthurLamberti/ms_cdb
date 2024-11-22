package com.arthurlamberti.cdb.application.wallet.retrieve.list;

import com.arthurlamberti.cdb.application.wallet.retrieve.get.GetWalletOutput;
import com.arthurlamberti.cdb.domain.paper.PaperID;
import com.arthurlamberti.cdb.domain.wallet.Wallet;

import java.util.List;

public record ListWalletOutput (
        String id,
        Double amount,
        String customerId,
        List<String> paperIdsList
) {
    public static ListWalletOutput from(Wallet wallet) {
        return new ListWalletOutput(
                wallet.getId().getValue(),
                wallet.getAmount(),
                wallet.getCustomerId(),
                toStringList(wallet.getPaperIDs())
        );
    }

    private static List<String> toStringList(List<PaperID> paperIDS) {
        return paperIDS
                .stream()
                .map(PaperID::getValue).
                toList();
    }
}