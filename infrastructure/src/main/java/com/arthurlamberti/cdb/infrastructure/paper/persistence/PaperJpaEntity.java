package com.arthurlamberti.cdb.infrastructure.paper.persistence;


import com.arthurlamberti.cdb.domain.paper.Paper;
import com.arthurlamberti.cdb.domain.paper.PaperID;
import com.arthurlamberti.cdb.infrastructure.wallet.persistence.WalletJpaEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "papers")
@Table(name = "papers")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PaperJpaEntity {

    @Id
    private String id;

    @Column(name = "value", nullable = false)
    private Double value;

    @OneToMany(mappedBy = "paper", cascade = CascadeType.ALL)
    private List<WalletJpaEntity> wallet;

    public static PaperJpaEntity from(final Paper aPaper){
        return new PaperJpaEntity(
                aPaper.getId().getValue(),
                aPaper.getValue(),
                null
        );
    }


    public Paper toAggregate(){
        return Paper.with(
                PaperID.from(this.id),
                this.value
        );
    }
}
