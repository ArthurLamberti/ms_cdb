package com.arthurlamberti.cdb.application.wallet.retrieve.get;

import com.arthurlamberti.cdb.domain.paper.PaperID;
import com.arthurlamberti.cdb.domain.wallet.Wallet;

import java.util.List;

public record GetWalletOutput(
        String id,
        Double amount,
        String customerId,
        List<String> paperIdsList
) {

    public static GetWalletOutput from(Wallet wallet) {
        return new GetWalletOutput(
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
