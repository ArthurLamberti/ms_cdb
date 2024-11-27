package com.arthurlamberti.cdb.infrastructure.transaction_paper.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionPaperJpaEntity, String> {
}
