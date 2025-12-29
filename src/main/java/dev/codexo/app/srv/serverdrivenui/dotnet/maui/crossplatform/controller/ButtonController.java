package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.controller;


import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.Constants;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.button.ButtonStyleDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.button.ButtonStyleUpdateDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.service.buttons.ButtonStyleUpdateService;
import dev.codexo.app.srv.serverdrivenui.model.entity.ProjectPlatformEntity;
import dev.codexo.app.srv.serverdrivenui.service.multitenant.ApiKeyValidatorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/styles/v1/dotnet-maui-cross-platform/buttons")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Button Styles", description = "API for managing .NET MAUI Button styles")
public class ButtonController {

    private final ButtonStyleUpdateService buttonStyleUpdateService;
    private final ApiKeyValidatorService apiKeyValidator;

    @PutMapping("/{buttonId}")
    public ResponseEntity<ButtonStyleDto> updateButtonStyle(
            @PathVariable String buttonId,
            @RequestBody ButtonStyleUpdateDto updateDto,
            @RequestHeader("X-API-Key") String apiKey)
    {
        // Validate API key and get project-platform
        ProjectPlatformEntity projectPlatform = apiKeyValidator
                .validateAndGetProjectPlatform(apiKey, Constants.DOTNET_MAUI_CROSS_PLATFORM);

        // Update with security check
        ButtonStyleDto response = buttonStyleUpdateService
                .updateButtonStyle(buttonId, updateDto, projectPlatform);

        return ResponseEntity.ok(response);
    }
}
