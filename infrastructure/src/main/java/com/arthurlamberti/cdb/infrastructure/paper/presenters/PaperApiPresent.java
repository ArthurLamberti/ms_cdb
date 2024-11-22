package com.arthurlamberti.cdb.infrastructure.paper.presenters;

import com.arthurlamberti.cdb.application.paper.retrieve.get.GetPaperOutput;
import com.arthurlamberti.cdb.application.paper.retrieve.list.ListPaperOutput;
import com.arthurlamberti.cdb.infrastructure.paper.models.GetPaperResponse;
import com.arthurlamberti.cdb.infrastructure.paper.models.ListPaperResponse;

public interface PaperApiPresent {
    static ListPaperResponse present(final ListPaperOutput output) {
        return new ListPaperResponse(
                output.id(),
                output.value()
        );
    }

    static GetPaperResponse present(final GetPaperOutput output) {
        return new GetPaperResponse(
                output.id(),
                output.value()
        );
    }

}
