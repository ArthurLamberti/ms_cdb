package com.arthurlamberti.cdb.domain.exceptions;

import com.arthurlamberti.cdb.domain.validation.Error;

import java.util.List;
import java.util.Optional;

public class DomainException extends NoStacktraceException {
    protected final List<Error> errors;

    public DomainException(String aMessage, final List<Error> erros) {
        super(aMessage);
        this.errors = erros;
    }

    public static DomainException with(Error error) {
        return new DomainException(error.message(), List.of(error));
    }

    public Optional<Error> getFirstError() {
        return Optional.of(errors.get(0));
    }

    public List<Error> getErrors() {
        return errors;
    }
}
