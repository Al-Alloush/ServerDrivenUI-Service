package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.controller;


import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.ThemeWrapperDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.button.ButtonStyleDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.button.ButtonStyleUpdateDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.service.StyleUpdateService;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.service.StyleQueryService;
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
@RequestMapping("/dotnetmaui/crossplatform/style")
public class DontNetCrossPlatformController {

    private final StyleQueryService styleQueryService;
    private final StyleUpdateService buttonStyleUpdateService;

    public DontNetCrossPlatformController(StyleQueryService styleQueryService, StyleUpdateService buttonStyleUpdateService) {
        this.styleQueryService = styleQueryService;
        this.buttonStyleUpdateService = buttonStyleUpdateService;
    }

    // Health check endpoint
    @GetMapping("/ping")
    public ResponseEntity<@NonNull String> ping() {
        return ResponseEntity.ok("DotnetMauiButtonStyleController is alive!");
    }

    @GetMapping
    public ThemeWrapperDto getStyleForImeterRecorder() {
        return styleQueryService.getButtonStylesForProjectSlug("imeterrecorder");
    }


    @PutMapping("/imeterrecorder/buttons/{buttonId}")
    public ResponseEntity<ButtonStyleDto> updateButtonstyle(
            @PathVariable String buttonId,
            @RequestBody ButtonStyleUpdateDto updateDto
    ) {
        var response = buttonStyleUpdateService.updateButtonStyle(buttonId, updateDto);
        return ResponseEntity.ok(response);
    }


}
