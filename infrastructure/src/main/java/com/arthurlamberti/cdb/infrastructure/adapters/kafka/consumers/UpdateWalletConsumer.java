package com.arthurlamberti.cdb.infrastructure.adapters.kafka.consumers;

import com.arthurlamberti.cdb.domain.enums.TransactionStatus;
import com.arthurlamberti.cdb.domain.enums.TransactionType;
import com.arthurlamberti.cdb.domain.exceptions.NotFoundException;
import com.arthurlamberti.cdb.domain.validation.Error;
import com.arthurlamberti.cdb.domain.wallet.Wallet;
import com.arthurlamberti.cdb.domain.wallet.WalletID;
import com.arthurlamberti.cdb.infrastructure.config.json.Json;
import com.arthurlamberti.cdb.infrastructure.transaction_paper.models.UpdateWalletResponse;
import com.arthurlamberti.cdb.infrastructure.transaction_paper.persistence.TransactionRepository;
import com.arthurlamberti.cdb.infrastructure.wallet.persistence.WalletJpaEntity;
import com.arthurlamberti.cdb.infrastructure.wallet.persistence.WalletRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UpdateWalletConsumer {

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;

    public UpdateWalletConsumer(final TransactionRepository transactionRepository, WalletRepository walletRepository) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
    }

    @KafkaListener(topics = "cgr.wallet.update.response")
    public void listen(String message) {
        final var updatedResponse = Json.readValue(message, UpdateWalletResponse.class);
        final var transaction = transactionRepository.findById(updatedResponse.transactionId()).orElseThrow(() -> NotFoundException.with(new Error("Transaction not found")));

        final var wallet = walletRepository.findById(transaction.getWalletId()).orElseThrow(() -> NotFoundException.with(new Error("Wallet not found")));

        final var updatedWallet = Wallet.with(
                WalletID.from(wallet.getId()),
                transaction.getType() == TransactionType.BUY ? wallet.getAmount() + transaction.getAmount() : wallet.getAmount() - transaction.getAmount(),
                wallet.getCustomerId(),
                wallet.getPaper().toAggregate()
        );
        transactionRepository.save(transaction.changeStatus(transaction,TransactionStatus.EXECUTED));
        walletRepository.save(WalletJpaEntity.from(updatedWallet));
    }

}
