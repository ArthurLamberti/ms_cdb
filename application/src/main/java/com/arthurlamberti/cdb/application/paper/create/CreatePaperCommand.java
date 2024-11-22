package com.arthurlamberti.cdb.application.paper.create;

public record CreatePaperCommand(
        Double value
) {
    public static CreatePaperCommand with(final Double expectedValue) {
        return new CreatePaperCommand(expectedValue);
    }
}
