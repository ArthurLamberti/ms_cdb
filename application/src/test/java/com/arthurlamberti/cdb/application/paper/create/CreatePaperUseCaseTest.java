package com.arthurlamberti.cdb.application.paper.create;

import com.arthurlamberti.cdb.application.UseCaseTest;
import com.arthurlamberti.cdb.domain.Fixture;
import com.arthurlamberti.cdb.domain.exceptions.NotificationException;
import com.arthurlamberti.cdb.domain.paper.PaperGateway;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CreatePaperUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultCreatePaperUseCase useCase;

    @Mock
    private PaperGateway paperGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(paperGateway);
    }

    @Test
    public void givenAValidCommand_whenCallsCreatePaper_shouldReturnPaperId() {
        final var expectedValue = Fixture.positiveDoubleNumber();
        final var aCommand = CreatePaperCommand.with(expectedValue);

        when(paperGateway.create(any())).thenAnswer(returnsFirstArg());

        final var actualResult = useCase.execute(aCommand);
        assertNotNull(actualResult);
        assertNotNull(actualResult.id());
        verify(paperGateway, times(1)).create(any());
    }

    @Test
    public void givenAInvalidNullValue_whenCallsCreatePaper_shouldReturnAnError(){
        final Double expectedValue = null;
        final var aCommand = CreatePaperCommand.with(expectedValue);
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'value' should not be null";

        final var actualException = assertThrows(NotificationException.class, () -> useCase.execute(aCommand));

        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());
        verify(paperGateway,never()).create(any());
    }

    @Test
    public void givenAInvalidZeroValue_whenCallsCreatePaper_shouldReturnAnError(){
        final var expectedValue = 0D;
        final var aCommand = CreatePaperCommand.with(expectedValue);
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'value' should be greater than 0";

        final var actualException = assertThrows(NotificationException.class, () -> useCase.execute(aCommand));

        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());
        verify(paperGateway,never()).create(any());
    }

    @Test
    public void givenAInvalidNegativeValue_whenCallsCreatePaper_shouldReturnAnError(){
        final var expectedValue = Fixture.negativeDoubleNumber();
        final var aCommand = CreatePaperCommand.with(expectedValue);
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'value' should be greater than 0";

        final var actualException = assertThrows(NotificationException.class, () -> useCase.execute(aCommand));

        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());
        verify(paperGateway,never()).create(any());
    }

    @Test
    public void givenAGatewayError_whenCallsListPaper_shouldReturnAnException() {
        final var expectedValue = Fixture.positiveDoubleNumber();
        final var aCommand = CreatePaperCommand.with(expectedValue);
        final var expectedErrorMessage = "Gateway error";

        doThrow(new IllegalStateException(expectedErrorMessage)).when(paperGateway).create(any());
        final var actualException = assertThrows(IllegalStateException.class, () -> useCase.execute(aCommand));

        assertEquals(expectedErrorMessage, actualException.getMessage());
        verify(paperGateway, times(1)).create(any());
    }

}