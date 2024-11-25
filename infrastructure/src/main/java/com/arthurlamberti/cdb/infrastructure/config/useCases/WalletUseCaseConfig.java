package com.arthurlamberti.cdb.infrastructure.config.useCases;

import com.arthurlamberti.cdb.application.wallet.create.CreateWalletUseCase;
import com.arthurlamberti.cdb.application.wallet.create.DefaultCreateWalletUseCase;
import com.arthurlamberti.cdb.application.wallet.paper.buy.BuyPaperWalletUseCase;
import com.arthurlamberti.cdb.application.wallet.paper.buy.DefaultBuyPaperWalletUseCase;
import com.arthurlamberti.cdb.application.wallet.paper.sell.DefaultSellPaperWalletUseCase;
import com.arthurlamberti.cdb.application.wallet.paper.sell.SellPaperWalletUseCase;
import com.arthurlamberti.cdb.application.wallet.retrieve.get.DefaultGetWalletUseCase;
import com.arthurlamberti.cdb.application.wallet.retrieve.get.GetWalletUseCase;
import com.arthurlamberti.cdb.application.wallet.retrieve.list.DefaultListWalletUseCase;
import com.arthurlamberti.cdb.application.wallet.retrieve.list.ListWalletUseCase;
import com.arthurlamberti.cdb.domain.adapters.feing.CustomerExternal;
import com.arthurlamberti.cdb.domain.adapters.kafka.WalletProducerKafka;
import com.arthurlamberti.cdb.domain.paper.PaperGateway;
import com.arthurlamberti.cdb.domain.wallet.WalletGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WalletUseCaseConfig {

    private final WalletGateway walletGateway;
    private final PaperGateway paperGateway;
    private final CustomerExternal customerExternal;
    private final WalletProducerKafka walletProducerKafka;

    public WalletUseCaseConfig(
            final WalletGateway walletGateway,
            final PaperGateway paperGateway,
            final CustomerExternal customerExternal,
            final WalletProducerKafka walletProducerKafka
    ) {
        this.walletGateway = walletGateway;
        this.paperGateway = paperGateway;
        this.customerExternal = customerExternal;
        this.walletProducerKafka = walletProducerKafka;
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

    @Bean
    public BuyPaperWalletUseCase buyPaperWalletUseCase() {
        return new DefaultBuyPaperWalletUseCase(walletGateway, paperGateway, customerExternal, walletProducerKafka);
    }

    @Bean
    public SellPaperWalletUseCase sellPaperWalletUseCase() {
        return new DefaultSellPaperWalletUseCase(walletGateway, paperGateway, customerExternal, walletProducerKafka);
    }

}
