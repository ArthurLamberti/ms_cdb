package com.arthurlamberti.cdb.application.wallet.create;

import com.arthurlamberti.cdb.application.UseCaseTest;
import com.arthurlamberti.cdb.application.paper.create.CreatePaperCommand;
import com.arthurlamberti.cdb.application.paper.create.DefaultCreatePaperUseCase;
import com.arthurlamberti.cdb.domain.Fixture;
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
import static org.mockito.Mockito.when;

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
        final var aCommand = CreateWalletCommand.with(expectedAmount, expectedCustomerId);

        when(walletGateway.create(any())).thenAnswer(returnsFirstArg());

        final var actualResult = useCase.execute(aCommand);
        assertNotNull(actualResult);
        assertNotNull(actualResult.walletId());
    }

    @Test
    @Disabled
    public void givenAValidCommandWithAmount0_whenCallsCreatePaper_shouldReturnPaperId() {
    }

    @Test
    @Disabled
    public void givenAInvalidNullAmount_whenCallsCreatePaper_shouldReturnPaperId() {
    }

    @Test
    @Disabled
    public void givenAInvalidNegativeAmount_whenCallsCreatePaper_shouldReturnPaperId() {
    }

    @Test
    @Disabled
    public void givenAInvalidNullCustomerId_whenCallsCreatePaper_shouldReturnPaperId() {
    }

    @Test
    @Disabled
    public void givenAInvalidEmptyCustomerId_whenCallsCreatePaper_shouldReturnPaperId() {
    }
}