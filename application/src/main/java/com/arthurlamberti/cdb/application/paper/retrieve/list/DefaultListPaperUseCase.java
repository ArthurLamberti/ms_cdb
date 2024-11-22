package com.arthurlamberti.cdb.application.paper.retrieve.list;

import com.arthurlamberti.cdb.domain.paper.PaperGateway;

import java.util.List;

public class DefaultListPaperUseCase extends ListPaperUseCase{

    private final PaperGateway paperGateway;

    public DefaultListPaperUseCase(
            final PaperGateway paperGateway
    ) {
        this.paperGateway = paperGateway;
    }

    @Override
    public List<ListPaperOutput> execute() {
        return this.paperGateway.findAll()
                .stream()
                .map(ListPaperOutput::from)
                .toList();
    }
}
