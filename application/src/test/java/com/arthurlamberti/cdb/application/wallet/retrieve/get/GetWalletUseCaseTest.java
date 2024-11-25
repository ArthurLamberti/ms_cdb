package com.arthurlamberti.cdb.application.wallet.retrieve.get;

import com.arthurlamberti.cdb.application.UseCaseTest;
import com.arthurlamberti.cdb.domain.Fixture;
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
import static org.mockito.Mockito.when;

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