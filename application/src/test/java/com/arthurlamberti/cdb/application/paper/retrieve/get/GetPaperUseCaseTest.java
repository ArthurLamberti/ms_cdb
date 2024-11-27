package com.arthurlamberti.cdb.application.paper.retrieve.get;

import com.arthurlamberti.cdb.application.UseCaseTest;
import com.arthurlamberti.cdb.application.paper.create.CreatePaperCommand;
import com.arthurlamberti.cdb.domain.Fixture;
import com.arthurlamberti.cdb.domain.exceptions.NotFoundException;
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
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

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
        final var expectedPaper = Paper.newPaper(Fixture.positiveDoubleNumber());
        final var expectedId = expectedPaper.getId();

        when(paperGateway.findById(any())).thenReturn(Optional.of(expectedPaper));

        final var actualResult = useCase.execute(expectedId.getValue());
        assertNotNull(actualResult);
        assertEquals(expectedPaper.getId().getValue(), actualResult.id());
        assertEquals(expectedPaper.getValue(), actualResult.value());
    }

    @Test
    public void givenAInvalidId_whenCallsGetPaper_shouldReturnAnException() {
        final var expectedPaper = Paper.newPaper(Fixture.positiveDoubleNumber());
        final var expectedId = expectedPaper.getId().getValue();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "Paper with ID %s was not found".formatted(expectedId);

        when(paperGateway.findById(any())).thenReturn(Optional.empty());

        final var actualException = assertThrows(NotFoundException.class, () -> useCase.execute(expectedId));

        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());

        verify(paperGateway, times(1)).findById(any());
    }

    @Test
    public void givenAGatewayError_whenCallsListPaper_shouldReturnAnException() {
        final var expectedValue = Fixture.positiveDoubleNumber();
        final var expectedId = Fixture.uuid();
        final var expectedErrorMessage = "Gateway error";

        doThrow(new IllegalStateException(expectedErrorMessage)).when(paperGateway).findById(any());
        final var actualException = assertThrows(IllegalStateException.class, () -> useCase.execute(expectedId));

        assertEquals(expectedErrorMessage, actualException.getMessage());
        verify(paperGateway, times(1)).findById(any());
    }
}