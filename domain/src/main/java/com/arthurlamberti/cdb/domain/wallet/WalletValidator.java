package com.arthurlamberti.cdb.domain.wallet;

import com.arthurlamberti.cdb.domain.paper.PaperID;
import com.arthurlamberti.cdb.domain.validation.Error;
import com.arthurlamberti.cdb.domain.validation.ValidationHandler;
import com.arthurlamberti.cdb.domain.validation.Validator;

import java.util.List;

import static java.util.Objects.isNull;

public class WalletValidator extends Validator {

    private final Wallet wallet;

    public WalletValidator(final ValidationHandler aHandler, final Wallet wallet) {
        super(aHandler);
        this.wallet = wallet;
    }

    @Override
    public void validate() {
        checkAmount();
        checkCustomerId();
        checkPaperIdsList();
    }

    private void checkAmount(){
        final var amount = wallet.getAmount();
        if (isNull(amount)) {
            this.validationHandler().append(new Error("'amount' should not be null"));
            return;
        }

        if (amount < 0) {
            this.validationHandler().append(new Error("'amount' should be greater then or equals 0"));
        }
    }
    private void checkCustomerId(){
        final var customerId = wallet.getCustomerId();
        if (isNull(customerId)){
            this.validationHandler().append(new Error("'customerId' should not be null"));
            return;
        }
        if (customerId.isBlank()){
            this.validationHandler().append(new Error("'customerId' should not be empty"));
        }
    }

    private void checkPaperIdsList(){
        if (isNull(wallet.getPaperIDs())) {
            this.validationHandler().append(new Error("'paperIDs should not be null'"));
        }
    }
}
