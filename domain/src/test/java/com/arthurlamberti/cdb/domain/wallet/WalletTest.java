package com.arthurlamberti.cdb.domain.wallet;

import com.arthurlamberti.cdb.domain.Fixture;
import com.arthurlamberti.cdb.domain.UnitTest;
import com.arthurlamberti.cdb.domain.exceptions.NotificationException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WalletTest extends UnitTest {

    @Test
    public void givenAValidParams_whenCallsNewWallet_shouldInstantiatIt() {
        final var expectedAmount = Fixture.positiveNumber();
        final var expectedCustomerId = Fixture.uuid();
        final var expectedPaperId = Fixture.uuid();

        final var actualWallet = Wallet.newWallet(expectedAmount, expectedCustomerId,expectedPaperId);

        assertNotNull(actualWallet);
        assertNotNull(actualWallet.getId());
        assertEquals(expectedAmount, actualWallet.getAmount());
        assertEquals(expectedCustomerId, actualWallet.getCustomerId());
    }

    @Test
    public void givenAValidParamsWithAmount0_whenCallsNewWallet_shouldInstantiatIt() {
        final var expectedAmount = 0;
        final var expectedCustomerId = Fixture.uuid();

        final var expectedPaperId = Fixture.uuid();

        final var actualWallet = Wallet.newWallet(expectedAmount, expectedCustomerId,expectedPaperId);

        assertNotNull(actualWallet);
        assertNotNull(actualWallet.getId());
        assertEquals(expectedAmount, actualWallet.getAmount());
        assertEquals(expectedCustomerId, actualWallet.getCustomerId());
    }

    @Test
    public void givenAnInvalidNullAmount_whenCallsNewWallet_shouldReceiveAnException() {
        final Integer expectedAmount = null;
        final var expectedCustomerId = Fixture.uuid();
        final var expectedPaperId = Fixture.uuid();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'amount' should not be null";
        final var actualException = assertThrows(NotificationException.class, () -> Wallet.newWallet(expectedAmount, expectedCustomerId, expectedPaperId));

        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());
    }

    @Test
    public void givenAnInvalidNegativeAmount_whenCallsNewWallet_shouldReceiveAnException() {
        final var expectedAmount = Fixture.negativeNumber();
        final var expectedCustomerId = Fixture.uuid();
        final var expectedPaperId = Fixture.uuid();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'amount' should be greater then or equals 0";
        final var actualException = assertThrows(NotificationException.class, () -> Wallet.newWallet(expectedAmount, expectedCustomerId, expectedPaperId));

        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());
    }

    @Test
    public void givenAnInvalidNullCustomerId_whenCallsNewWallet_shouldReceiveAnException() {
        final var expectedAmount = Fixture.positiveNumber();
        final String expectedCustomerId = null;
        final var expectedPaperId = Fixture.uuid();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'customerId' should not be null";
        final var actualException = assertThrows(NotificationException.class, () -> Wallet.newWallet(expectedAmount, expectedCustomerId, expectedPaperId));

        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());
    }

    @Test
    public void givenAnInvalidEmptyCustomerId_whenCallsNewWallet_shouldReceiveAnException() {
        final var expectedAmount = Fixture.positiveNumber();
        final var expectedCustomerId = " ";
        final var expectedPaperId = Fixture.uuid();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'customerId' should not be empty";
        final var actualException = assertThrows(NotificationException.class, () -> Wallet.newWallet(expectedAmount, expectedCustomerId, expectedPaperId));

        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());
    }

    @Test
    @Disabled
    public void givenAValidWallet_whenCallsAddPaper_shouldAddOnWallet(){}

    @Test
    @Disabled
    public void givenAnInvalidNullPaperId_whenCallsAddPaper_shouldReturnAnError(){}

    @Test
    @Disabled
    public void givenAnInvalidEmptyPaperId_whenCallsAddPaper_shouldReturnAnError(){}
}
