package com.arthurlamberti.cdb.application.wallet.retrieve.list;

import com.arthurlamberti.cdb.application.UseCaseTest;
import com.arthurlamberti.cdb.domain.Fixture;
import com.arthurlamberti.cdb.domain.paper.Paper;
import com.arthurlamberti.cdb.domain.wallet.Wallet;
import com.arthurlamberti.cdb.domain.wallet.WalletGateway;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ListWalletUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultListWalletUseCase useCase;

    @Mock
    private WalletGateway walletGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(walletGateway);
    }

    @Test
    public void givenAnExistedList_whenCallsListWallet_shouldReturnAList() {
        final var expectedList = List.of(
                Wallet.newWallet(Fixture.positiveNumber(), Fixture.uuid()),
                Wallet.newWallet(Fixture.positiveNumber(), Fixture.uuid()),
                Wallet.newWallet(Fixture.positiveNumber(), Fixture.uuid())
        );

        when(walletGateway.findAll()).thenReturn(expectedList);

        final var actualResult = useCase.execute();
        assertNotNull(actualResult);
        assertEquals(expectedList.size(), actualResult.size());
    }

    @Test
    @Disabled
    public void givenAnEmptyList_whenCallsListPaper_shouldReturnAnEmptyResult() {
    }

    @Test
    @Disabled
    public void givenAGatewayError_whenCallsListPaper_shouldReturnAnException() {
    }
}