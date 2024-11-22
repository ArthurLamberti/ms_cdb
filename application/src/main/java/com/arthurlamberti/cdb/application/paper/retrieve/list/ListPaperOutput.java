package com.arthurlamberti.cdb.application.paper.retrieve.list;

import com.arthurlamberti.cdb.domain.paper.Paper;

public record ListPaperOutput(
        String id,
        Double value
) {
    public static ListPaperOutput from(Paper paper) {
        return new ListPaperOutput(paper.getId().getValue(), paper.getValue());
    }
}
