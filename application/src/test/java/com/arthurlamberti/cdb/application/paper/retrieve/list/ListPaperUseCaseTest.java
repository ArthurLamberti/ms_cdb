package com.arthurlamberti.cdb.application.paper.retrieve.list;

import com.arthurlamberti.cdb.application.UseCaseTest;
import com.arthurlamberti.cdb.application.paper.retrieve.get.DefaultGetPaperUseCase;
import com.arthurlamberti.cdb.domain.Fixture;
import com.arthurlamberti.cdb.domain.paper.Paper;
import com.arthurlamberti.cdb.domain.paper.PaperGateway;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ListPaperUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultListPaperUseCase useCase;

    @Mock
    private PaperGateway paperGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(paperGateway);
    }

    @Test
    public void givenAnExistedList_whenCallsListPaper_shouldReturnAList() {
        final var expectedList = List.of(
                Paper.newPaper(Fixture.positiveNumber()),
                Paper.newPaper(Fixture.positiveNumber())
        );

        when(paperGateway.findAll()).thenReturn(expectedList);

        final var actualResult = useCase.execute();
        assertNotNull(actualResult);
    }

    @Test
    @Disabled
    public void givenAnEmptyList_whenCallsListPaper_shouldReturnAnEmptyResult() {
    }

    @Test
    @Disabled
    public void givenAGatewayError_whenCallsListPaper_shouldReturnAnException() {
    }
}