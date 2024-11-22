package com.arthurlamberti.cdb.infrastructure.api.controllers;

import com.arthurlamberti.cdb.application.wallet.create.CreateWalletCommand;
import com.arthurlamberti.cdb.application.wallet.create.CreateWalletUseCase;
import com.arthurlamberti.cdb.application.wallet.retrieve.get.GetWalletUseCase;
import com.arthurlamberti.cdb.application.wallet.retrieve.list.ListWalletUseCase;
import com.arthurlamberti.cdb.infrastructure.api.WalletApi;
import com.arthurlamberti.cdb.infrastructure.paper.models.CreatePaperRequest;
import com.arthurlamberti.cdb.infrastructure.paper.models.GetPaperResponse;
import com.arthurlamberti.cdb.infrastructure.wallet.models.CreateWalletRequest;
import com.arthurlamberti.cdb.infrastructure.wallet.models.GetWalletResponse;
import com.arthurlamberti.cdb.infrastructure.wallet.models.ListWalletResponse;
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

    public WalletController(
            final CreateWalletUseCase createWalletUseCase,
            final ListWalletUseCase listWalletUseCase,
            final GetWalletUseCase getWalletUseCase) {
        this.createWalletUseCase = createWalletUseCase;
        this.listWalletUseCase = listWalletUseCase;
        this.getWalletUseCase = getWalletUseCase;
    }

    @Override
    public ResponseEntity<?> createWallet(CreateWalletRequest input) {
        final var aCommand = CreateWalletCommand.with(0.0,input.customerId());

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
}
