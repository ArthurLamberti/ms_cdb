package com.arthurlamberti.cdb.domain.paper;

import com.arthurlamberti.cdb.domain.validation.Error;
import com.arthurlamberti.cdb.domain.validation.ValidationHandler;
import com.arthurlamberti.cdb.domain.validation.Validator;

import static java.util.Objects.isNull;

public class PaperValidator extends Validator {

    private final Paper paper;

    protected PaperValidator(
            final ValidationHandler aHandler,
            final Paper aPaper
    ) {
        super(aHandler);
        this.paper = aPaper;
    }

    @Override
    public void validate() {
        checkValue();
    }

    private void checkValue(){
        final var value = paper.getValue();
        if (isNull(value)){
            this.validationHandler().append(new Error("'value' should not be null"));
            return;
        }
        if (value <= 0) {
            this.validationHandler().append(new Error("'value' should be greater than 0"));
        }
    }
}
