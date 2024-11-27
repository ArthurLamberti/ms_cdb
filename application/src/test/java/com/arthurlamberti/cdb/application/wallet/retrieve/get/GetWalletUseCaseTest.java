package com.arthurlamberti.cdb.application.wallet.retrieve.get;

import com.arthurlamberti.cdb.application.UseCaseTest;
import com.arthurlamberti.cdb.domain.Fixture;
import com.arthurlamberti.cdb.domain.exceptions.NotFoundException;
import com.arthurlamberti.cdb.domain.paper.PaperID;
import com.arthurlamberti.cdb.domain.wallet.Wallet;
import com.arthurlamberti.cdb.domain.wallet.WalletGateway;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GetWalletUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultGetWalletUseCase useCase;

    @Mock
    private WalletGateway walletGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(walletGateway);
    }

    @Test
    public void givenAValidIdWithoutPaper_whenCallsGetWallet_shouldReturnWallet() {
        final var expectedWallet = Wallet.newWallet(Fixture.positiveNumber(), Fixture.uuid(), Fixture.uuid());
        final var expectedWalletId = expectedWallet.getId();

        when(walletGateway.findById(any())).thenReturn(Optional.of(expectedWallet));

        final var actualWallet = useCase.execute(expectedWalletId.getValue());
        assertNotNull(actualWallet);
        assertEquals(expectedWallet.getAmount(), actualWallet.amount());
        assertEquals(expectedWallet.getCustomerId(), actualWallet.customerId());
        assertEquals(expectedWallet.getPaperID(), actualWallet.paperId());
    }

    @Test
    public void givenAInvalidId_whenCallsGetPaper_shouldReturnAnException() {
        final var expectedWallet = Wallet.newWallet(Fixture.positiveNumber(), Fixture.uuid(), Fixture.uuid());
        final var expectedWalletId = expectedWallet.getId().getValue();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "Wallet with ID %s was not found".formatted(expectedWalletId);

        when(walletGateway.findById(any())).thenReturn(Optional.empty());

        final var actualException = assertThrows(NotFoundException.class, () -> useCase.execute(expectedWalletId));

        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());

        verify(walletGateway, times(1)).findById(any());
    }
}