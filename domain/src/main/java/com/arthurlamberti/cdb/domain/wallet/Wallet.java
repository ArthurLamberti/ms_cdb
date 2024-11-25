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

    private final Integer amount;
    private final String customerId;
    private final String paperID;

    protected Wallet(
            final WalletID anId,
            final Integer anAmount,
            final String aCustomerId,
            final String paperID
    ) {
        super(anId);
        this.amount = anAmount;
        this.customerId = aCustomerId;
        this.paperID = paperID;

        selfValidate();
    }

    public static Wallet with(final WalletID anId, final Integer amount, String customerId, String paperID) {
        return new Wallet(
                anId,
                amount,
                customerId,
                paperID
        );
    }

    private void selfValidate() {
        final var notification = Notification.create();
        validate(notification);
        if (notification.hasError())
            throw new NotificationException("Failed to create a Wallet", notification);
    }

    public static Wallet newWallet(
            final Integer anAmount,
            final String customerId,
            final String paperID
    ) {
        final var anId = WalletID.unique();
        return new Wallet(anId, anAmount, customerId, paperID);
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new WalletValidator(handler, this).validate();
    }

    public Wallet incrementAmount(Integer amount) {
        return new Wallet(getId(), getAmount() + amount, getCustomerId(), getPaperID());
    }

    public Wallet decrementAmount(Integer amount) {
        return new Wallet(getId(), getAmount() - amount, getCustomerId(), getPaperID());
    }
}
