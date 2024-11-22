package com.arthurlamberti.cdb.domain.wallet;

import com.arthurlamberti.cdb.domain.AggregateRoot;
import com.arthurlamberti.cdb.domain.exceptions.NotificationException;
import com.arthurlamberti.cdb.domain.paper.PaperID;
import com.arthurlamberti.cdb.domain.utils.InstantUtils;
import com.arthurlamberti.cdb.domain.validation.ValidationHandler;
import com.arthurlamberti.cdb.domain.validation.handler.Notification;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Getter
public class Wallet extends AggregateRoot<WalletID> {

    private final Double amount;
    private final String customerId;
    private final List<PaperID> paperIDs;

    protected Wallet(
            final WalletID anId,
            final Double anAmount,
            final String aCustomerId,
            final List<PaperID> paperIDs
    ) {
        super(anId);
        this.amount = anAmount;
        this.customerId = aCustomerId;
        this.paperIDs = paperIDs;

        selfValidate();
    }

    public static Wallet with(final WalletID anId, final Double amount, String customerId) {
        return new Wallet(
                anId,
                amount,
                customerId,
                null
        );
    }

    private void selfValidate() {
        final var notification = Notification.create();
        validate(notification);
        if (notification.hasError())
            throw new NotificationException("Failed to create a Wallet", notification);
    }

    public static Wallet newWallet(
            final Double anAmount,
            final String customerId
    ) {
        final var anId = WalletID.unique();
        return new Wallet(anId, anAmount, customerId, new ArrayList<>());
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new WalletValidator(handler, this).validate();
    }

    public void addPaper(final PaperID paperID) {
        this.paperIDs.add(paperID);
    }
}
