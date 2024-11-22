package com.arthurlamberti.cdb.infrastructure.api;


import com.arthurlamberti.cdb.infrastructure.paper.models.CreatePaperRequest;
import com.arthurlamberti.cdb.infrastructure.paper.models.GetPaperResponse;
import com.arthurlamberti.cdb.infrastructure.paper.models.ListPaperResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "papers")
@Tag(name = "Papers")
public interface PaperAPI {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create a new Paper")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was throw"),
            @ApiResponse(responseCode = "500", description = "Internal error server"),
    })
    ResponseEntity<?> createPaper(@RequestBody CreatePaperRequest input);

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "List all papers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paper list successfully"),
            @ApiResponse(responseCode = "500", description = "Internal error server"),
    })
    List<ListPaperResponse> listPapers();

    @GetMapping(
            value = "/{customerId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "get paper by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get paper successfully"),
            @ApiResponse(responseCode = "500", description = "Internal error server"),
    })
    GetPaperResponse getPaperById(@PathVariable String customerId);
}
