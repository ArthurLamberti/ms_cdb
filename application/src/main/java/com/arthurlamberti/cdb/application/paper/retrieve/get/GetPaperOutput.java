package com.arthurlamberti.cdb.application.paper.retrieve.get;

import com.arthurlamberti.cdb.domain.paper.Paper;

public record GetPaperOutput(
        String id,
        Double value
) {
    public static GetPaperOutput from(Paper paper) {
        return new GetPaperOutput(
                paper.getId().getValue(),
                paper.getValue()
        );
    }
}
