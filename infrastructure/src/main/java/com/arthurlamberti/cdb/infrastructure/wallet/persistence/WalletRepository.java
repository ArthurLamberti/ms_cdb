package com.arthurlamberti.cdb.infrastructure.wallet.persistence;

import com.arthurlamberti.cdb.domain.wallet.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<WalletJpaEntity, String> {
    Optional<WalletJpaEntity> findByCustomerIdAndPaperId(String customerId, String paperId);
}
