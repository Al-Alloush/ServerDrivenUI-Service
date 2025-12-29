package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.controller;

import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.entry.EntryStyleDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.entry.EntryStyleUpdateDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.service.entry.EntryStyleUpdateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/styles/v1/dotnet-maui-cross-platform/entries")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Entry Styles", description = "API for managing .NET MAUI entry styles")
public class EntryController {

    private final EntryStyleUpdateService entryStyleUpdateService;

    @PutMapping("/{entryId}")
    @Operation(
            summary = "Update an entry style",
            description = "Updates specific properties of an entry style. Only the provided fields will be updated."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Entry style updated successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EntryStyleDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Entry style not found",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request data",
                    content = @Content
            )
    })
    public ResponseEntity<EntryStyleDto> updateEntryStyle(
            @Parameter(description = "ID of the entry style to update", required = true)
            @PathVariable String entryId,
            @Parameter(description = "Entry style properties to update", required = true)
            @RequestBody EntryStyleUpdateDto updateDto
    ) {
        log.info("Received request to update entry style with ID: {}", entryId);
        EntryStyleDto updatedEntryStyle = entryStyleUpdateService.updateEntryStyle(entryId, updateDto);
        return ResponseEntity.ok(updatedEntryStyle);
    }
}

