package com.arthurlamberti.cdb.application.wallet.retrieve.get;

import com.arthurlamberti.cdb.domain.paper.PaperID;
import com.arthurlamberti.cdb.domain.wallet.Wallet;

import java.util.List;

public record GetWalletOutput(
        String id,
        Integer amount,
        String customerId,
        String paperId
) {

    public static GetWalletOutput from(Wallet wallet) {
        return new GetWalletOutput(
                wallet.getId().getValue(),
                wallet.getAmount(),
                wallet.getCustomerId(),
                wallet.getPaper().getId().getValue()
        );
    }
}
