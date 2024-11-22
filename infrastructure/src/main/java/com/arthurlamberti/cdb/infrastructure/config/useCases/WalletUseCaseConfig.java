package com.arthurlamberti.cdb.infrastructure.config.useCases;

import com.arthurlamberti.cdb.application.wallet.create.CreateWalletUseCase;
import com.arthurlamberti.cdb.application.wallet.create.DefaultCreateWalletUseCase;
import com.arthurlamberti.cdb.application.wallet.retrieve.get.DefaultGetWalletUseCase;
import com.arthurlamberti.cdb.application.wallet.retrieve.get.GetWalletUseCase;
import com.arthurlamberti.cdb.application.wallet.retrieve.list.DefaultListWalletUseCase;
import com.arthurlamberti.cdb.application.wallet.retrieve.list.ListWalletUseCase;
import com.arthurlamberti.cdb.domain.wallet.WalletGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WalletUseCaseConfig {

    private final WalletGateway walletGateway;

    public WalletUseCaseConfig(
            final WalletGateway walletGateway
    ) {
        this.walletGateway = walletGateway;
    }

    @Bean
    public CreateWalletUseCase createWalletUseCase() {
        return new DefaultCreateWalletUseCase(walletGateway);
    }

    @Bean
    public ListWalletUseCase listWalletUseCase() {
        return new DefaultListWalletUseCase(walletGateway);
    }

    @Bean
    public GetWalletUseCase getWalletUseCase() {
        return new DefaultGetWalletUseCase(walletGateway);
    }

}
