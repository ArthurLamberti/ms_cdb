package com.arthurlamberti.cdb.domain.paper;

import com.arthurlamberti.cdb.domain.Fixture;
import com.arthurlamberti.cdb.domain.UnitTest;
import com.arthurlamberti.cdb.domain.exceptions.NotificationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PaperTest extends UnitTest {

    @Test
    public void givenAValidParams_whenCallsNewPaper_shouldInstantiateAPaper() {
        final var expectedValue = Fixture.positiveNumber();

        final var actualPaper = Paper.newPaper(expectedValue);
        assertNotNull(actualPaper);
        assertNotNull(actualPaper.getId());
        assertEquals(expectedValue, actualPaper.getValue());
    }

    @Test
    public void givenAInvalidNullValue_whenCallsNewPaper_shouldReturnAnException(){
        final Double expectedValue = null;

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'value' should not be null";

        final var actualerror = assertThrows(
                NotificationException.class,
                () -> Paper.newPaper(expectedValue)
        );

        assertEquals(expectedErrorCount, actualerror.getErrors().size());
        assertEquals(expectedErrorMessage, actualerror.getFirstError().get().message());
    }


    @Test
    public void givenAInvalidZeroValue_whenCallsNewPaper_shouldReturnAnException(){
        final var expectedValue = 0.0;

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'value' should be greater than 0";

        final var actualerror = assertThrows(
                NotificationException.class,
                () -> Paper.newPaper(expectedValue)
        );

        assertEquals(expectedErrorCount, actualerror.getErrors().size());
        assertEquals(expectedErrorMessage, actualerror.getFirstError().get().message());
    }


    @Test
    public void givenAInvalidNegativeValue_whenCallsNewPaper_shouldReturnAnException(){
        final var expectedValue = Fixture.negativeNumber();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'value' should be greater than 0";

        final var actualerror = assertThrows(
                NotificationException.class,
                () -> Paper.newPaper(expectedValue)
        );

        assertEquals(expectedErrorCount, actualerror.getErrors().size());
        assertEquals(expectedErrorMessage, actualerror.getFirstError().get().message());
    }
}