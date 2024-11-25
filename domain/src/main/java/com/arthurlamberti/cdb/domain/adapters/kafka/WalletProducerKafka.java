package com.arthurlamberti.cdb.domain.adapters.kafka;

import com.arthurlamberti.cdb.domain.wallet.Wallet;

public interface WalletProducerKafka {
    void updateBalance(Wallet wallet, Double diffToUpdate);
}
