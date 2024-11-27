package com.arthurlamberti.cdb.infrastructure.transaction_paper;

import com.arthurlamberti.cdb.domain.enums.TransactionStatus;
import com.arthurlamberti.cdb.domain.enums.TransactionType;
import com.arthurlamberti.cdb.domain.transaction.TransactionGateway;
import com.arthurlamberti.cdb.domain.wallet.Wallet;
import com.arthurlamberti.cdb.infrastructure.transaction_paper.persistence.TransactionPaperJpaEntity;
import com.arthurlamberti.cdb.infrastructure.transaction_paper.persistence.TransactionRepository;
import org.springframework.stereotype.Component;

@Component
public class TransactionMySQLGateway implements TransactionGateway {

    private final TransactionRepository transactionRepository;

    public TransactionMySQLGateway(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public String create(Wallet updatedWallet, Integer amount, TransactionType transactionType) {
        return transactionRepository
                .save(
                        TransactionPaperJpaEntity.from(updatedWallet.getId().getValue(),
                                updatedWallet.getPaper().getId().getValue(),
                                amount,
                                transactionType,
                                TransactionStatus.CREATED))
                .getId();
    }
}
