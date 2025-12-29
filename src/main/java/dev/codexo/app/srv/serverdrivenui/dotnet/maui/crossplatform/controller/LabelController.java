package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.controller;


import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.label.LabelStyleDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.label.LabelStyleUpdateDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.service.label.LabelStyleUpdateService;
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
@RequestMapping("/api/styles/v1/dotnet-maui-cross-platform/labels")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Label Styles", description = "API for managing .NET MAUI label styles")
public class LabelController {

    private final LabelStyleUpdateService labelStyleUpdateService;

    @PutMapping("/{labelId}")
    @Operation(
            summary = "Update a label style",
            description = "Updates specific properties of a label style. Only the provided fields will be updated."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Label style updated successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LabelStyleDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Label style not found",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request data",
                    content = @Content
            )
    })
    public ResponseEntity<LabelStyleDto> updateLabelStyle(
            @Parameter(description = "ID of the label style to update", required = true)
            @PathVariable String labelId,
            @Parameter(description = "Label style properties to update", required = true)
            @RequestBody LabelStyleUpdateDto updateDto
    ) {
        log.info("Received request to update label style with ID: {}", labelId);
        LabelStyleDto updatedLabelStyle = labelStyleUpdateService.updateLabelStyle(labelId, updateDto);
        return ResponseEntity.ok(updatedLabelStyle);
    }
}
