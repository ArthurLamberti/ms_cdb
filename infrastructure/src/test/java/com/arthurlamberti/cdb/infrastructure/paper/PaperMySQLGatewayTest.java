package com.arthurlamberti.cdb.infrastructure.paper;

import com.arthurlamberti.cdb.MySQLGatewayTest;
import com.arthurlamberti.cdb.domain.Fixture;
import com.arthurlamberti.cdb.domain.paper.Paper;
import com.arthurlamberti.cdb.domain.paper.PaperGateway;
import com.arthurlamberti.cdb.infrastructure.paper.persistence.PaperRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@MySQLGatewayTest
public class PaperMySQLGatewayTest {

    @Autowired
    private PaperGateway paperGateway;

    @Autowired
    private PaperRepository paperRepository;

    @Test
    public void givenAValidPaper_whenCallsCreatePaper_shouldPersistIt() {
        final var aPaper = Paper.newPaper(Fixture.positiveDoubleNumber());

        assertEquals(0, paperRepository.count());
        final var actualPaper = paperGateway.create(aPaper);

        assertEquals(1, paperRepository.count());
        assertEquals(actualPaper.getId().getValue(), aPaper.getId().getValue());
        assertEquals(actualPaper.getValue(), aPaper.getValue());

        final var persistedPaper = paperRepository.findById(aPaper.getId().getValue()).get();
        assertEquals(persistedPaper.getId(), aPaper.getId().getValue());
        assertEquals(persistedPaper.getValue(), aPaper.getValue());
    }

    @Test
    @Disabled
    public void givenAPrePersistedWallet_whenCallsFindByIdAndExists_shouldReturnIt() {

    }

    @Test
    @Disabled
    public void givenAPrePersistedWallet_whenCallsFindByIdAndNotExists_shouldReturnIt() {

    }

    @Test
    @Disabled
    public void givenAEmptyWalletList_whenCallsFindAll_shouldReturnEmpty() {

    }

    @Test
    @Disabled
    public void givenAPrePersistedWallet_whenCallsFindAll_shouldReturnAll() {

    }
}