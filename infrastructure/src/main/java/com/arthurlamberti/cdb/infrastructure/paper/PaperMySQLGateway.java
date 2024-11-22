package com.arthurlamberti.cdb.infrastructure.paper;

import com.arthurlamberti.cdb.domain.paper.Paper;
import com.arthurlamberti.cdb.domain.paper.PaperGateway;
import com.arthurlamberti.cdb.domain.paper.PaperID;
import com.arthurlamberti.cdb.infrastructure.paper.persistence.PaperJpaEntity;
import com.arthurlamberti.cdb.infrastructure.paper.persistence.PaperRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PaperMySQLGateway implements PaperGateway {

    private final PaperRepository paperRepository;

    public PaperMySQLGateway(final PaperRepository paperRepository) {
        this.paperRepository = paperRepository;
    }

    @Override
    public Paper create(Paper aPaper) {
        return this.paperRepository.save(PaperJpaEntity.from(aPaper)).toAggregate();
    }

    @Override
    public Optional<Paper> findById(PaperID anId) {
        return this.paperRepository.findById(anId.getValue())
                .map(PaperJpaEntity::toAggregate);
    }

    @Override
    public List<Paper> findAll() {
        return this.paperRepository.findAll()
                .stream()
                .map(PaperJpaEntity::toAggregate)
                .toList();
    }
}
