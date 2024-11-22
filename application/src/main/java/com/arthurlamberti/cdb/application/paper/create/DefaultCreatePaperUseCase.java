package com.arthurlamberti.cdb.application.paper.create;

import com.arthurlamberti.cdb.domain.exceptions.NotificationException;
import com.arthurlamberti.cdb.domain.paper.Paper;
import com.arthurlamberti.cdb.domain.paper.PaperGateway;
import com.arthurlamberti.cdb.domain.validation.handler.Notification;

import static java.util.Objects.requireNonNull;

public class DefaultCreatePaperUseCase extends CreatePaperUseCase {

    private final PaperGateway paperGateway;

    public DefaultCreatePaperUseCase(
            final PaperGateway paperGateway
    ) {
        this.paperGateway = requireNonNull(paperGateway);
    }

    @Override
    public CreatePaperOutput execute(CreatePaperCommand aCommand) {
        final var notification = Notification.create();
        final var aPaper = notification.validate(
                () -> Paper.newPaper(aCommand.value())
        );
        if (notification.hasError()) {
            throw new NotificationException("Could not create Aggregate Paper", notification);
        }
        return CreatePaperOutput.from(paperGateway.create(aPaper));
    }
}
