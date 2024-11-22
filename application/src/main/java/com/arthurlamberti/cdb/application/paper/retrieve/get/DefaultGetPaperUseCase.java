package com.arthurlamberti.cdb.application.paper.retrieve.get;

import com.arthurlamberti.cdb.application.paper.create.DefaultCreatePaperUseCase;
import com.arthurlamberti.cdb.domain.exceptions.NotFoundException;
import com.arthurlamberti.cdb.domain.paper.Paper;
import com.arthurlamberti.cdb.domain.paper.PaperGateway;
import com.arthurlamberti.cdb.domain.paper.PaperID;

public class DefaultGetPaperUseCase extends GetPaperUseCase{

    private final PaperGateway paperGateway;

    public DefaultGetPaperUseCase(
            final PaperGateway paperGateway
    ) {
        this.paperGateway = paperGateway;
    }

    @Override
    public GetPaperOutput execute(String input) {
        return this.paperGateway.findById(PaperID.from(input))
                .map(GetPaperOutput::from)
                .orElseThrow(() -> NotFoundException.with(Paper.class, PaperID.from(input)));
    }
}
