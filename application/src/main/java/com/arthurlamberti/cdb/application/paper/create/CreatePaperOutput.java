package com.arthurlamberti.cdb.application.paper.create;

import com.arthurlamberti.cdb.domain.paper.Paper;

public record CreatePaperOutput (String id){
    public static CreatePaperOutput from(final Paper paper) {
        return new CreatePaperOutput(paper.getId().getValue());
    }

    public static CreatePaperOutput from(final String paperId) {
        return new CreatePaperOutput(paperId);
    }
}
