package com.arthurlamberti.cdb.infrastructure.adapters.kafka.producers;

import com.arthurlamberti.cdb.domain.wallet.Wallet;
import com.arthurlamberti.cdb.infrastructure.adapters.kafka.models.UpdateWalletBalance;
import com.arthurlamberti.cdb.infrastructure.config.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class UpdateWalletProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String key, UpdateWalletBalance updateRecord) {
        kafkaTemplate.send(topic, key, Json.writeValueAsString(updateRecord));
    }
}
