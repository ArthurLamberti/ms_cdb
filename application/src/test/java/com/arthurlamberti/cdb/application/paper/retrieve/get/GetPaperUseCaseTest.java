package com.arthurlamberti.cdb.application.paper.retrieve.get;

import com.arthurlamberti.cdb.application.UseCaseTest;
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

public class GetPaperUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultGetPaperUseCase useCase;

    @Mock
    private PaperGateway paperGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(paperGateway);
    }

    @Test
    public void givenAValidId_whenCallsGetPaper_shouldReturnPaper() {
        final var expectedPaper = Paper.newPaper(Fixture.positiveNumber());
        final var expectedId = expectedPaper.getId();

        when(paperGateway.findById(any())).thenReturn(Optional.of(expectedPaper));

        final var actualResult = useCase.execute(expectedId.getValue());
        assertNotNull(actualResult);
        assertEquals(expectedPaper.getId().getValue(), actualResult.id());
        assertEquals(expectedPaper.getValue(), actualResult.value());
    }

    @Test
    @Disabled
    public void givenAInvalidId_whenCallsGetPaper_shouldReturnAnException() {
    }

    @Test
    @Disabled
    public void givenAnEmptyId_whenCallsGetPaper_shouldReturnAnException() {
    }

    @Test
    @Disabled
    public void givenANullId_whenCallsGetPaper_shouldReturnAnException() {
    }

    @Test
    @Disabled
    public void givenAGatewayError_whenCallsListPaper_shouldReturnAnException() {
    }
}