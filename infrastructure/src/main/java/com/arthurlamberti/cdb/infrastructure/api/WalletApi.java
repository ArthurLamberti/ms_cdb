package com.arthurlamberti.cdb.infrastructure.api;


import com.arthurlamberti.cdb.infrastructure.paper.models.CreatePaperRequest;
import com.arthurlamberti.cdb.infrastructure.paper.models.GetPaperResponse;
import com.arthurlamberti.cdb.infrastructure.paper.models.ListPaperResponse;
import com.arthurlamberti.cdb.infrastructure.wallet.models.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "wallets")
@Tag(name = "Wallets")
public interface WalletApi {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create a new Wallet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was throw"),
            @ApiResponse(responseCode = "500", description = "Internal error server"),
    })
    ResponseEntity<?> createWallet(@RequestBody CreateWalletRequest input);

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "List all wallets")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Wallet list successfully"),
            @ApiResponse(responseCode = "500", description = "Internal error server"),
    })
    List<ListWalletResponse> listWallets();

    @GetMapping(
            value = "/{walletId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "get Wallet by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get wallet successfully"),
            @ApiResponse(responseCode = "500", description = "Internal error server"),
    })
    GetWalletResponse getWalletById(@PathVariable String walletId);

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/{customerId}/buy/{paperId}"
    )
    @Operation(summary = "Buy a paper")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was throw"),
            @ApiResponse(responseCode = "500", description = "Internal error server"),
    })
    ResponseEntity<?> buyPaper(@RequestBody BuyPaperRequest input, @PathVariable String customerId, @PathVariable String paperId);


    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/{customerId}/sell/{paperId}"
    )
    @Operation(summary = "Buy a paper")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was throw"),
            @ApiResponse(responseCode = "500", description = "Internal error server"),
    })
    ResponseEntity<?> sellPaper(@RequestBody SellPaperRequest input, @PathVariable String customerId, @PathVariable String paperId);
}
