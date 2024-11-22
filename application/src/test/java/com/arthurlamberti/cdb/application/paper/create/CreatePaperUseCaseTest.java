package com.arthurlamberti.cdb.application.paper.create;

import com.arthurlamberti.cdb.application.UseCaseTest;
import com.arthurlamberti.cdb.domain.Fixture;
import com.arthurlamberti.cdb.domain.paper.PaperGateway;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
        final var expectedValue = Fixture.positiveNumber();
        final var aCommand = CreatePaperCommand.with(expectedValue);

        when(paperGateway.create(any())).thenAnswer(returnsFirstArg());

        final var actualResult = useCase.execute(aCommand);
        assertNotNull(actualResult);
        assertNotNull(actualResult.paperId());
    }

    @Disabled
    @Test
    public void givenAInvalidNullValue_whenCallsCreatePaper_shouldReturnAnError(){
    }

    @Disabled
    @Test
    public void givenAInvalidZeroValue_whenCallsCreatePaper_shouldReturnAnError(){
    }

    @Disabled
    @Test
    public void givenAInvalidNegativeValue_whenCallsCreatePaper_shouldReturnAnError(){
    }

    @Test
    @Disabled
    public void givenAGatewayError_whenCallsListPaper_shouldReturnAnException() {
    }

}