package com.arthurlamberti.cdb.domain.paper;

import com.arthurlamberti.cdb.domain.AggregateRoot;
import com.arthurlamberti.cdb.domain.exceptions.NotificationException;
import com.arthurlamberti.cdb.domain.validation.ValidationHandler;
import com.arthurlamberti.cdb.domain.validation.handler.Notification;
import lombok.Getter;

@Getter
public class Paper extends AggregateRoot<PaperID> {

    private Double value;

    protected Paper(
            final PaperID anId,
            final Double value
    ) {
        super(anId);
        this.value = value;

        selfValidate();
    }

    public static Paper newPaper(
            final Double aValue
    ) {
        final var anId = PaperID.unique();
        return new Paper(anId, aValue);
    }

    public static Paper with(final PaperID anId, final Double value) {
        return new Paper(anId, value);
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new PaperValidator(handler, this).validate();
    }

    private void selfValidate() {
        final var notification = Notification.create();
        validate(notification);
        if (notification.hasError()) {
            throw new NotificationException("Failed to create a Paper", notification);
        }
    }
}
