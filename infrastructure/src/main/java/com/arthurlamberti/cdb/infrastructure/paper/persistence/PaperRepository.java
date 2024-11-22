package com.arthurlamberti.cdb.infrastructure.paper.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaperRepository extends JpaRepository<PaperJpaEntity, String> {
}
