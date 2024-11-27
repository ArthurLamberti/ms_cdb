package com.arthurlamberti.cdb.infrastructure.transaction_paper.persistence;

import com.arthurlamberti.cdb.domain.enums.TransactionStatus;
import com.arthurlamberti.cdb.domain.enums.TransactionType;
import com.arthurlamberti.cdb.domain.utils.IdUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "transaction_paper")
@Table(name = "Transaction_paper")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class TransactionPaperJpaEntity {

    @Id
    private String id;

    @Column(name = "wallet_id", nullable = false)
    private String walletId;

    @Column(name = "paper_id", nullable = false)
    private String paperId;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    private TransactionType type;
    private TransactionStatus status;

    public static TransactionPaperJpaEntity from(String walletId, String paperId, Integer amount, TransactionType type, TransactionStatus status) {
        return new TransactionPaperJpaEntity(IdUtils.uuid(), walletId, paperId, amount, type, status);
    }

    public TransactionPaperJpaEntity changeStatus(TransactionPaperJpaEntity transaction, TransactionStatus transactionStatus) {
        return new TransactionPaperJpaEntity(transaction.getId(), transaction.getWalletId(), transaction.getPaperId(), transaction.getAmount(), transaction.getType(), transactionStatus);
    }
}
