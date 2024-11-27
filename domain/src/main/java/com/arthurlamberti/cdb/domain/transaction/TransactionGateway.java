package com.arthurlamberti.cdb.domain.transaction;

import com.arthurlamberti.cdb.domain.enums.TransactionType;
import com.arthurlamberti.cdb.domain.wallet.Wallet;

public interface TransactionGateway {
    String create(Wallet updatedWallet, Integer amount, TransactionType transactionType);
}
