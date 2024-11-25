package com.arthurlamberti.cdb.infrastructure.api.controllers;

import com.arthurlamberti.cdb.application.wallet.create.CreateWalletCommand;
import com.arthurlamberti.cdb.application.wallet.create.CreateWalletUseCase;
import com.arthurlamberti.cdb.application.wallet.paper.buy.BuyPaperCommand;
import com.arthurlamberti.cdb.application.wallet.paper.buy.BuyPaperWalletUseCase;
import com.arthurlamberti.cdb.application.wallet.paper.sell.SellPaperCommand;
import com.arthurlamberti.cdb.application.wallet.paper.sell.SellPaperWalletUseCase;
import com.arthurlamberti.cdb.application.wallet.retrieve.get.GetWalletUseCase;
import com.arthurlamberti.cdb.application.wallet.retrieve.list.ListWalletUseCase;
import com.arthurlamberti.cdb.infrastructure.api.WalletApi;
import com.arthurlamberti.cdb.infrastructure.paper.models.CreatePaperRequest;
import com.arthurlamberti.cdb.infrastructure.paper.models.GetPaperResponse;
import com.arthurlamberti.cdb.infrastructure.wallet.models.*;
import com.arthurlamberti.cdb.infrastructure.wallet.presenters.WalletApiPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class WalletController implements WalletApi {

    private final CreateWalletUseCase createWalletUseCase;
    private final ListWalletUseCase listWalletUseCase;
    private final GetWalletUseCase getWalletUseCase;
    private final BuyPaperWalletUseCase buyPaperWalletUseCase;
    private final SellPaperWalletUseCase sellPaperWalletUseCase;

    public WalletController(
            final CreateWalletUseCase createWalletUseCase,
            final ListWalletUseCase listWalletUseCase,
            final GetWalletUseCase getWalletUseCase,
            final BuyPaperWalletUseCase buyPaperWalletUseCase,
            final SellPaperWalletUseCase sellPaperWalletUseCase) {
        this.createWalletUseCase = createWalletUseCase;
        this.listWalletUseCase = listWalletUseCase;
        this.getWalletUseCase = getWalletUseCase;
        this.buyPaperWalletUseCase = buyPaperWalletUseCase;
        this.sellPaperWalletUseCase = sellPaperWalletUseCase;
    }

    @Override
    public ResponseEntity<?> createWallet(CreateWalletRequest input) {
        final var aCommand = CreateWalletCommand.with(0,input.customerId(), input.paperId());

        final var output = createWalletUseCase.execute(aCommand);

        return ResponseEntity.created(URI.create("/wallets/" + output.id())).body(output);
    }

    @Override
    public List<ListWalletResponse> listWallets() {
        return this.listWalletUseCase.execute()
                .stream()
                .map(WalletApiPresenter::present)
                .toList();
    }

    @Override
    public GetWalletResponse getWalletById(String walletId) {
        return WalletApiPresenter.present(this.getWalletUseCase.execute(walletId));
    }

    @Override
    public ResponseEntity<?> buyPaper(BuyPaperRequest input, String customerId, String paperId) {
        final var aCommand = BuyPaperCommand.with(customerId, paperId, input.amount());

        buyPaperWalletUseCase.execute(aCommand);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> sellPaper(SellPaperRequest input, String customerId, String paperId) {
        final var aCommand = SellPaperCommand.with(customerId, paperId, input.amount());

        sellPaperWalletUseCase.execute(aCommand);
        return ResponseEntity.noContent().build();
    }
}
