package com.arthurlamberti.cdb.infrastructure.wallet;

import com.arthurlamberti.cdb.domain.wallet.Wallet;
import com.arthurlamberti.cdb.domain.wallet.WalletGateway;
import com.arthurlamberti.cdb.domain.wallet.WalletID;
import com.arthurlamberti.cdb.infrastructure.adapters.kafka.producers.UpdateWalletProducer;
import com.arthurlamberti.cdb.infrastructure.paper.persistence.PaperJpaEntity;
import com.arthurlamberti.cdb.infrastructure.wallet.persistence.WalletJpaEntity;
import com.arthurlamberti.cdb.infrastructure.wallet.persistence.WalletRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class WalletMySQLGateway implements WalletGateway {

    private final WalletRepository walletRepository;

    public WalletMySQLGateway(
            final WalletRepository walletRepository
    ) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet create(Wallet aWallet) {
        return this.walletRepository.save(WalletJpaEntity.from(aWallet)).toAggregate();
    }

    @Override
    public Optional<Wallet> findById(WalletID anId) {
        return this.walletRepository.findById(anId.getValue())
                .map(WalletJpaEntity::toAggregate);
    }

    @Override
    public List<Wallet> findAll() {
        return this.walletRepository.findAll()
                .stream()
                .map(WalletJpaEntity::toAggregate)
                .toList();
    }

    @Override
    public Optional<Wallet> findByCustomerIdAndPaperId(String customerId, String paperId) {
        return this.walletRepository.findByCustomerIdAndPaperId(customerId, paperId)
                .map(WalletJpaEntity::toAggregate);
    }

    @Override
    public Wallet update(Wallet updatedWallet) {
        return this.walletRepository.save(WalletJpaEntity.from(updatedWallet)).toAggregate();
    }
}
