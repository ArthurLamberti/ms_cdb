package com.arthurlamberti.cdb.domain.wallet;

import com.arthurlamberti.cdb.domain.AggregateRoot;
import com.arthurlamberti.cdb.domain.exceptions.NotificationException;
import com.arthurlamberti.cdb.domain.paper.Paper;
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
    private final Paper paper;

    protected Wallet(
            final WalletID anId,
            final Integer anAmount,
            final String aCustomerId,
            final Paper paper
    ) {
        super(anId);
        this.amount = anAmount;
        this.customerId = aCustomerId;
        this.paper = paper;

        selfValidate();
    }

    public static Wallet with(final WalletID anId, final Integer amount, String customerId, Paper paper) {
        return new Wallet(
                anId,
                amount,
                customerId,
                paper
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
            final Paper paper
    ) {
        final var anId = WalletID.unique();
        return new Wallet(anId, anAmount, customerId, paper);
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new WalletValidator(handler, this).validate();
    }

    public Wallet incrementAmount(Integer amount) {
        return new Wallet(getId(), getAmount() + amount, getCustomerId(), getPaper());
    }

    public Wallet decrementAmount(Integer amount) {
        return new Wallet(getId(), getAmount() - amount, getCustomerId(), getPaper());
    }
}
