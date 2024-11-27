package com.arthurlamberti.cdb.infrastructure.adapters.kafka;

import com.arthurlamberti.cdb.domain.adapters.kafka.WalletProducerKafka;
import com.arthurlamberti.cdb.domain.enums.TransactionType;
import com.arthurlamberti.cdb.domain.wallet.Wallet;
import com.arthurlamberti.cdb.infrastructure.adapters.kafka.models.UpdateWalletBalance;
import com.arthurlamberti.cdb.infrastructure.adapters.kafka.producers.UpdateWalletProducer;
import org.springframework.stereotype.Component;

@Component
public class KafkaGateway implements WalletProducerKafka {

    private final UpdateWalletProducer updateWalletProducer;

    public KafkaGateway(UpdateWalletProducer updateWalletProducer) {
        this.updateWalletProducer = updateWalletProducer;
    }

    @Override
    public void updateBalance(Wallet wallet, Double diffToUpdate, TransactionType type, String transactionId) {
        final var updateRecord = UpdateWalletBalance.with(wallet.getCustomerId(), diffToUpdate, type, transactionId);
        updateWalletProducer.sendMessage("cgr.wallet.update", wallet.getCustomerId(), updateRecord);
    }
}
