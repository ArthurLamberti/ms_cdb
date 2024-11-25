package com.arthurlamberti.cdb.domain.wallet;

import java.util.List;
import java.util.Optional;

public interface WalletGateway {
    Wallet create(final Wallet aWallet);
    Optional<Wallet> findById(WalletID anId);
    List<Wallet> findAll();
    Optional<Wallet> findByCustomerIdAndPaperId(String customerId, String paperId);

    Wallet update(Wallet updatedWallet);
}
