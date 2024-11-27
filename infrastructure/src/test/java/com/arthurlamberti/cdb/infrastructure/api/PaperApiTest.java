package com.arthurlamberti.cdb.infrastructure.api;

import com.arthurlamberti.cdb.ControllerTest;
import com.arthurlamberti.cdb.application.paper.create.CreatePaperCommand;
import com.arthurlamberti.cdb.application.paper.create.CreatePaperOutput;
import com.arthurlamberti.cdb.application.paper.create.CreatePaperUseCase;
import com.arthurlamberti.cdb.application.paper.retrieve.get.GetPaperOutput;
import com.arthurlamberti.cdb.application.paper.retrieve.get.GetPaperUseCase;
import com.arthurlamberti.cdb.application.paper.retrieve.list.ListPaperOutput;
import com.arthurlamberti.cdb.application.paper.retrieve.list.ListPaperUseCase;
import com.arthurlamberti.cdb.application.wallet.create.CreateWalletUseCase;
import com.arthurlamberti.cdb.application.wallet.paper.buy.BuyPaperWalletUseCase;
import com.arthurlamberti.cdb.application.wallet.paper.sell.SellPaperWalletUseCase;
import com.arthurlamberti.cdb.application.wallet.retrieve.get.GetWalletUseCase;
import com.arthurlamberti.cdb.application.wallet.retrieve.list.ListWalletUseCase;
import com.arthurlamberti.cdb.domain.Fixture;
import com.arthurlamberti.cdb.domain.paper.Paper;
import com.arthurlamberti.cdb.infrastructure.paper.models.CreatePaperRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ControllerTest
public class PaperApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreatePaperUseCase createPaperUseCase;

    @MockBean
    private GetPaperUseCase getPaperUseCase;

    @MockBean
    private ListPaperUseCase listPaperUseCase;

    @MockBean
    private CreateWalletUseCase createWalletUseCase;
    @MockBean
    private ListWalletUseCase listWalletUseCase;
    @MockBean
    private GetWalletUseCase getWalletUseCase;
    @MockBean
    private BuyPaperWalletUseCase buyPaperWalletUseCase;
    @MockBean
    private SellPaperWalletUseCase sellPaperWalletUseCase;

    @Test
    public void givenAValidCommand_whenCallsCreatePaper_shouldReturnPaperId() throws Exception{
        final var anInput = new CreatePaperRequest(Fixture.positiveDoubleNumber());
        final var expectedId = Fixture.uuid();

        when(createPaperUseCase.execute(any())).thenReturn(CreatePaperOutput.from(expectedId));
        final var aRequest = post("/papers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(anInput));
        final var response = this.mockMvc.perform(aRequest).andDo(print());

        response.andExpect(status().isCreated())
                .andExpect(header().string("Location", "/papers/" + expectedId))
                .andExpect(jsonPath("$.id", equalTo(expectedId)));
    }

    @Test
    public void givenAnExistedPaperId_whenCallsGetPaperById_shouldReturnPaper() throws Exception{
        final var aPaper = Paper.newPaper(Fixture.positiveDoubleNumber());

        when(getPaperUseCase.execute(any())).thenReturn(GetPaperOutput.from(aPaper));
        final var aRequest = get("/papers/"+aPaper.getId().getValue())
                .contentType(MediaType.APPLICATION_JSON);
        final var response = this.mockMvc.perform(aRequest).andDo(print());

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(aPaper.getId().getValue())))
                .andExpect(jsonPath("$.value", equalTo(aPaper.getValue())));
    }
    @Test
    public void givenAnExistedPaperId_whenCallsListPapers_shouldReturnPaper() throws Exception{
        final var aPaper = Paper.newPaper(Fixture.positiveDoubleNumber());

        when(listPaperUseCase.execute()).thenReturn(List.of(ListPaperOutput.from(aPaper)));
        final var aRequest = get("/papers")
                .contentType(MediaType.APPLICATION_JSON);
        final var response = this.mockMvc.perform(aRequest).andDo(print());

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", equalTo(aPaper.getId().getValue())))
                .andExpect(jsonPath("$[0].value", equalTo(aPaper.getValue())));
    }

    //TODO implmement more tests (errors...)
}
