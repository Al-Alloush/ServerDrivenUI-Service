package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.controller;

import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.Constants;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.button.ButtonStyleCreateDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.button.ButtonStyleDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.button.ButtonStyleUpdateDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.service.buttons.ButtonStyleCreateService;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.service.buttons.ButtonStyleDeleteService;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.service.buttons.ButtonStyleUpdateService;
import dev.codexo.app.srv.serverdrivenui.model.entity.ProjectPlatformEntity;
import dev.codexo.app.srv.serverdrivenui.service.multitenant.ApiKeyValidatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/styles/v1/dotnet-maui-cross-platform/buttons")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Button Styles", description = "API for managing .NET MAUI Button styles")
public class ButtonController {

    private final ButtonStyleCreateService buttonStyleCreateService;
    private final ButtonStyleUpdateService buttonStyleUpdateService;
    private final ButtonStyleDeleteService buttonStyleDeleteService;
    private final ApiKeyValidatorService apiKeyValidator;

    @PostMapping
    @Operation(summary = "Create a new button style", description = "Creates a new button style for a specific theme.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Button style created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ButtonStyleDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data or button style key already exists", content = @Content),
            @ApiResponse(responseCode = "401", description = "Invalid API key", content = @Content),
            @ApiResponse(responseCode = "404", description = "Theme not found", content = @Content)
    })
    public ResponseEntity<ButtonStyleDto> createButtonStyle(
            @Parameter(description = "Button style properties to create", required = true)
            @Valid @RequestBody ButtonStyleCreateDto createDto,
            @RequestHeader("X-API-Key") String apiKey) {
        apiKeyValidator.validateAndGetProjectPlatform(apiKey, Constants.DOTNET_MAUI_CROSS_PLATFORM);
        log.info("Received request to create button style with key: {} for theme: {}", createDto.getKey(), createDto.getThemeId());
        ButtonStyleDto createdButtonStyle = buttonStyleCreateService.createButtonStyle(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdButtonStyle);
    }

    @PutMapping("/{buttonId}")
    @Operation(summary = "Update a button style", description = "Updates specific properties of a button style.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Button style updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ButtonStyleDto.class))),
            @ApiResponse(responseCode = "401", description = "Invalid API key", content = @Content),
            @ApiResponse(responseCode = "404", description = "Button style not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content)
    })
    public ResponseEntity<ButtonStyleDto> updateButtonStyle(
            @Parameter(description = "ID of the button style to update", required = true)
            @PathVariable String buttonId,
            @Parameter(description = "Button style properties to update", required = true)
            @RequestBody ButtonStyleUpdateDto updateDto,
            @RequestHeader("X-API-Key") String apiKey) {
        ProjectPlatformEntity projectPlatform = apiKeyValidator
                .validateAndGetProjectPlatform(apiKey, Constants.DOTNET_MAUI_CROSS_PLATFORM);
        log.info("Received request to update button style with ID: {}", buttonId);
        ButtonStyleDto updatedButtonStyle = buttonStyleUpdateService.updateButtonStyle(buttonId, updateDto, projectPlatform);
        return ResponseEntity.ok(updatedButtonStyle);
    }

    @DeleteMapping("/{buttonId}")
    @Operation(summary = "Delete a button style", description = "Deletes a button style by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Button style deleted successfully"),
            @ApiResponse(responseCode = "401", description = "Invalid API key", content = @Content),
            @ApiResponse(responseCode = "404", description = "Button style not found", content = @Content)
    })
    public ResponseEntity<Void> deleteButtonStyle(
            @Parameter(description = "ID of the button style to delete", required = true)
            @PathVariable String buttonId,
            @RequestHeader("X-API-Key") String apiKey) {
        apiKeyValidator.validateAndGetProjectPlatform(apiKey, Constants.DOTNET_MAUI_CROSS_PLATFORM);
        log.info("Received request to delete button style with ID: {}", buttonId);
        buttonStyleDeleteService.deleteButtonStyle(buttonId);
        return ResponseEntity.noContent().build();
    }
}
