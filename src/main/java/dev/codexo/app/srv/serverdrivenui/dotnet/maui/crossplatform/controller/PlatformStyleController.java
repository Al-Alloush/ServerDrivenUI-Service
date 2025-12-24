package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.controller;


import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.ThemeWrapperDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.service.PlatformStyleService;
import dev.codexo.app.srv.serverdrivenui.model.entity.ProjectPlatformEntity;
import dev.codexo.app.srv.serverdrivenui.service.multitenant.ApiKeyValidatorService;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Exposes SDUI styles for the iMeterRecorder .NET MAUI client.
 * <p>
 * Your MAUI app is configured with:
 *   BaseAddress = <a href="https://srvdrvnui-dev.codexo.dev/">...</a>
 *   ButtonsEndpoint = "imeterrecorder/style/buttons"
 * <p>
 * So the full URL is:
 *   GET <a href="https://srvdrvnui-dev.codexo.dev/imeterrecorder/style/buttons">...</a>
 */
@RestController
@RequestMapping("/api/styles/v1/dotnet-maui-cross-platform")
public class PlatformStyleController {

    private final PlatformStyleService styleQueryService;
    private final ApiKeyValidatorService apiKeyValidator;

    public PlatformStyleController(PlatformStyleService styleQueryService, ApiKeyValidatorService apiKeyValidator) {
        this.styleQueryService = styleQueryService;
        this.apiKeyValidator = apiKeyValidator;
    }

    // Health check endpoint
    @GetMapping("/ping")
    public ResponseEntity<@NonNull String> ping() {
        return ResponseEntity.ok("DotnetMauiButtonStyleController is alive!");
    }

    @GetMapping
    public ResponseEntity<ThemeWrapperDto> getStyles(
            @RequestHeader("X-API-Key") String apiKey )
    {
        // Validate API key and get project-platform
        ProjectPlatformEntity projectPlatform = apiKeyValidator.validateAndGetProjectPlatform(apiKey, "DOTNET_MAUI_CROSS_PLATFORM");

        // Get styles
        var styles = styleQueryService.getPlatformStyle(projectPlatform);

        return ResponseEntity.ok(styles);
    }

}
