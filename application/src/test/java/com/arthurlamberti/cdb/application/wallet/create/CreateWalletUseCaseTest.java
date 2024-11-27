package com.arthurlamberti.cdb.application.wallet.create;

import com.arthurlamberti.cdb.application.UseCaseTest;
import com.arthurlamberti.cdb.application.paper.create.CreatePaperCommand;
import com.arthurlamberti.cdb.application.paper.create.DefaultCreatePaperUseCase;
import com.arthurlamberti.cdb.domain.Fixture;
import com.arthurlamberti.cdb.domain.exceptions.NotificationException;
import com.arthurlamberti.cdb.domain.paper.PaperGateway;
import com.arthurlamberti.cdb.domain.wallet.WalletGateway;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CreateWalletUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultCreateWalletUseCase useCase;

    @Mock
    private WalletGateway walletGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(walletGateway);
    }

    @Test
    public void givenAValidCommand_whenCallsCreatePaper_shouldReturnPaperId() {
        final var expectedAmount = Fixture.positiveNumber();
        final var expectedCustomerId = Fixture.uuid();
        final var expectedPaperId = Fixture.uuid();
        final var aCommand = CreateWalletCommand.with(expectedAmount, expectedCustomerId, expectedPaperId);

        when(walletGateway.create(any())).thenAnswer(returnsFirstArg());

        final var actualResult = useCase.execute(aCommand);
        assertNotNull(actualResult);
        assertNotNull(actualResult.id());
    }

    @Test
    public void givenAValidCommandWithAmount0_whenCallsCreatePaper_shouldReturnPaperId() {
        final var expectedAmount = 0;
        final var expectedCustomerId = Fixture.uuid();
        final var expectedPaperId = Fixture.uuid();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'amount' should be greater then 0";

        final var aCommand = CreateWalletCommand.with(expectedAmount, expectedCustomerId, expectedPaperId);

        final var actualException = assertThrows(NotificationException.class, () -> useCase.execute(aCommand));

        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());

        verify(walletGateway, never()).create(any());
    }

    @Test
    public void givenAInvalidNullAmount_whenCallsCreatePaper_shouldReturnPaperId() {
        final Integer expectedAmount = null;
        final var expectedCustomerId = Fixture.uuid();
        final var expectedPaperId = Fixture.uuid();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'amount' should not be null";

        final var aCommand = CreateWalletCommand.with(expectedAmount, expectedCustomerId, expectedPaperId);

        final var actualException = assertThrows(NotificationException.class, () -> useCase.execute(aCommand));

        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());

        verify(walletGateway, never()).create(any());
    }

    @Test
    public void givenAInvalidNegativeAmount_whenCallsCreatePaper_shouldReturnPaperId() {
        final var expectedAmount = Fixture.negativeNumber();
        final var expectedCustomerId = Fixture.uuid();
        final var expectedPaperId = Fixture.uuid();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'amount' should be greater then 0";

        final var aCommand = CreateWalletCommand.with(expectedAmount, expectedCustomerId, expectedPaperId);

        final var actualException = assertThrows(NotificationException.class, () -> useCase.execute(aCommand));

        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());

        verify(walletGateway, never()).create(any());
    }

    @Test
    public void givenAInvalidNullCustomerId_whenCallsCreatePaper_shouldReturnPaperId() {
        final var expectedAmount = Fixture.positiveNumber();
        final String expectedCustomerId = null;
        final var expectedPaperId = Fixture.uuid();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'customerId' should not be null";

        final var aCommand = CreateWalletCommand.with(expectedAmount, expectedCustomerId, expectedPaperId);

        final var actualException = assertThrows(NotificationException.class, () -> useCase.execute(aCommand));

        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());

        verify(walletGateway, never()).create(any());
    }

    @Test
    public void givenAInvalidEmptyCustomerId_whenCallsCreatePaper_shouldReturnPaperId() {
        final var expectedAmount = Fixture.positiveNumber();
        final var expectedCustomerId = " ";
        final var expectedPaperId = Fixture.uuid();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'customerId' should not be empty";

        final var aCommand = CreateWalletCommand.with(expectedAmount, expectedCustomerId, expectedPaperId);

        final var actualException = assertThrows(NotificationException.class, () -> useCase.execute(aCommand));

        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());

        verify(walletGateway, never()).create(any());
    }

    @Test
    public void givenAInvalidNullPaperId_whenCallsCreatePaper_shouldReturnPaperId() {
        final var expectedAmount = Fixture.positiveNumber();
        final var expectedCustomerId = Fixture.uuid();
        final String expectedPaperId = null;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'paperId' should not be null";

        final var aCommand = CreateWalletCommand.with(expectedAmount, expectedCustomerId, expectedPaperId);

        final var actualException = assertThrows(NotificationException.class, () -> useCase.execute(aCommand));

        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());

        verify(walletGateway, never()).create(any());
    }

    @Test
    public void givenAInvalidEmptyPaperId_whenCallsCreatePaper_shouldReturnPaperId() {
        final var expectedAmount = Fixture.positiveNumber();
        final var expectedCustomerId = Fixture.uuid();
        final var expectedPaperId = " ";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'paperId' should not be empty";

        final var aCommand = CreateWalletCommand.with(expectedAmount, expectedCustomerId, expectedPaperId);

        final var actualException = assertThrows(NotificationException.class, () -> useCase.execute(aCommand));

        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());

        verify(walletGateway, never()).create(any());
    }
}