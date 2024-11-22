package com.arthurlamberti.cdb.infrastructure.api.controllers;

import com.arthurlamberti.cdb.application.paper.create.CreatePaperCommand;
import com.arthurlamberti.cdb.application.paper.create.CreatePaperUseCase;
import com.arthurlamberti.cdb.application.paper.retrieve.get.GetPaperUseCase;
import com.arthurlamberti.cdb.application.paper.retrieve.list.ListPaperUseCase;
import com.arthurlamberti.cdb.infrastructure.api.PaperAPI;
import com.arthurlamberti.cdb.infrastructure.paper.models.CreatePaperRequest;
import com.arthurlamberti.cdb.infrastructure.paper.models.GetPaperResponse;
import com.arthurlamberti.cdb.infrastructure.paper.models.ListPaperResponse;
import com.arthurlamberti.cdb.infrastructure.paper.presenters.PaperApiPresent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class PaperController implements PaperAPI {

    private final CreatePaperUseCase createPaperUseCase;
    private final ListPaperUseCase listPaperUseCase;
    private final GetPaperUseCase getPaperUseCase;

    public PaperController(
            final CreatePaperUseCase createPaperUseCase,
            final ListPaperUseCase listPaperUseCase,
            final GetPaperUseCase getPaperUseCase) {
        this.createPaperUseCase = createPaperUseCase;
        this.listPaperUseCase = listPaperUseCase;
        this.getPaperUseCase = getPaperUseCase;
    }

    @Override
    public ResponseEntity<?> createPaper(CreatePaperRequest input) {
        final var aCommand = CreatePaperCommand.with(input.value());

        final var output = createPaperUseCase.execute(aCommand);

        return ResponseEntity.created(URI.create("/papers/" + output.id())).body(output);
    }

    @Override
    public List<ListPaperResponse> listPapers() {
        return this.listPaperUseCase.execute()
                .stream()
                .map(PaperApiPresent::present)
                .toList();
    }

    @Override
    public GetPaperResponse getPaperById(String customerId) {
        return PaperApiPresent.present(this.getPaperUseCase.execute(customerId));
    }
}
