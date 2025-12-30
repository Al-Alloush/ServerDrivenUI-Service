package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.controller;

import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.Constants;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.border.BorderStyleDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.border.BorderStyleUpdateDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.service.border.BorderStyleUpdateService;
import dev.codexo.app.srv.serverdrivenui.service.multitenant.ApiKeyValidatorService;
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
@RequestMapping("/api/styles/v1/dotnet-maui-cross-platform/borders")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Border Styles", description = "API for managing .NET MAUI Border styles")
public class BorderController {

    private final BorderStyleUpdateService borderStyleUpdateService;
    private final ApiKeyValidatorService apiKeyValidator;

    @PutMapping("/{borderId}")
    @Operation(summary = "Update a border style", description = "Updates specific properties of a border style.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Border style updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BorderStyleDto.class))),
            @ApiResponse(responseCode = "401", description = "Invalid API key", content = @Content),
            @ApiResponse(responseCode = "404", description = "Border style not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content)
    })
    public ResponseEntity<BorderStyleDto> updateBorderStyle(
            @Parameter(description = "ID of the border style to update", required = true)
            @PathVariable String borderId,
            @Parameter(description = "Border style properties to update", required = true)
            @RequestBody BorderStyleUpdateDto updateDto,
            @RequestHeader("X-API-Key") String apiKey) {
        apiKeyValidator.validateAndGetProjectPlatform(apiKey, Constants.DOTNET_MAUI_CROSS_PLATFORM);
        log.info("Received request to update border style with ID: {}", borderId);
        BorderStyleDto updatedBorderStyle = borderStyleUpdateService.updateBorderStyle(borderId, updateDto);
        return ResponseEntity.ok(updatedBorderStyle);
    }
}
