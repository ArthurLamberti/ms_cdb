package com.arthurlamberti.cdb.domain.exceptions;


import com.arthurlamberti.cdb.domain.validation.handler.Notification;

public class NotificationException extends DomainException {
    public NotificationException(String message, final Notification notification) {
        super(message, notification.getErrors());
    }
}
