package com.arthurlamberti.cdb.domain.paper;

import java.util.List;
import java.util.Optional;

public interface PaperGateway {
    Paper create(final Paper aPaper);
    Optional<Paper> findById(PaperID anId);

    List<Paper> findAll();
}
