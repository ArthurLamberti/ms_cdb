package com.arthurlamberti.cdb.infrastructure.wallet.persistence;


import com.arthurlamberti.cdb.domain.paper.Paper;
import com.arthurlamberti.cdb.domain.paper.PaperID;
import com.arthurlamberti.cdb.domain.wallet.Wallet;
import com.arthurlamberti.cdb.domain.wallet.WalletID;
import com.arthurlamberti.cdb.infrastructure.paper.persistence.PaperJpaEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "wallets")
@Table(name = "Wallets")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class WalletJpaEntity {

    @Id
    private String id;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "customer_id", nullable = false, unique = true)
    private String customerId;

    @ManyToOne
    @JoinColumn(name = "paper_id", nullable = false)
    private PaperJpaEntity paper;

    public static WalletJpaEntity from(final Wallet aWallet){
        return new WalletJpaEntity(
                aWallet.getId().getValue(),
                aWallet.getAmount(),
                aWallet.getCustomerId(),
                PaperJpaEntity.from(aWallet.getPaper())
        );
    }

    public Wallet toAggregate(){
        return Wallet.with(
                WalletID.from(this.id),
                this.amount,
                this.customerId,
                this.paper.toAggregate()
        );
    }
}
