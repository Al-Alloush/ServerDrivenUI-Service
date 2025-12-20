package dev.codexo.app.srv.serverdrivenui.controller;


import dev.codexo.app.srv.serverdrivenui.model.dto.dotnetmaui.DotnetMauiButtonStyleUpdateDto;
import dev.codexo.app.srv.serverdrivenui.model.dto.dotnetmaui.DotnetMauiButtonThemeWrapperDto;
import dev.codexo.app.srv.serverdrivenui.service.DotnetMauiButtonStyleUpdateService;
import dev.codexo.app.srv.serverdrivenui.service.DotnetMauiStyleQueryService;
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
@RequestMapping("/dotnetmaui/style")
public class DotnetMauiButtonStyleController {

    private final DotnetMauiStyleQueryService styleQueryService;
    private final DotnetMauiButtonStyleUpdateService buttonStyleUpdateService;

    public DotnetMauiButtonStyleController(DotnetMauiStyleQueryService styleQueryService, DotnetMauiButtonStyleUpdateService buttonStyleUpdateService) {
        this.styleQueryService = styleQueryService;
        this.buttonStyleUpdateService = buttonStyleUpdateService;
    }

    // Health check endpoint
    @GetMapping("/ping")
    public ResponseEntity<@NonNull String> ping() {
        return ResponseEntity.ok("DotnetMauiButtonStyleController is alive!");
    }

    /**
     * Returns all button styles for the "imeterrecorder" project and
     * the ".NET MAUI" platform.
     * <p>
     * The JSON shape matches what SduiRemoteStyleService expects:
     * <p>
     * {
     *   "light": [ { ...button style... }, ... ],
     *   "dark":  [ { ...button style... }, ... ]
     * }
     */
    @GetMapping("/imeterrecorder/buttons")
    public DotnetMauiButtonThemeWrapperDto getButtonsForImeterRecorder() {
        return styleQueryService.getButtonStylesForProjectSlug("imeterrecorder");
    }

    @PutMapping("/imeterrecorder/buttons/{buttonId}")
    public ResponseEntity<String> updateButtonStyle(
            @PathVariable String buttonId,
            @RequestBody DotnetMauiButtonStyleUpdateDto updateDto
    ) {
        var response = buttonStyleUpdateService.updateButtonStyle(buttonId, updateDto);
        if (response != null) {
            return ResponseEntity.ok("Button style updated successfully: " + response.getId());
        }
        return ResponseEntity.status(500).body("Failed to update button style.");
    }

}
