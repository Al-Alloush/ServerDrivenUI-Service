package dev.codexo.app.srv.serverdrivenui.controller;


import dev.codexo.app.srv.serverdrivenui.model.dto.dotnetmaui.DotnetMauiButtonThemeWrapperDto;
import dev.codexo.app.srv.serverdrivenui.service.DotnetMauiStyleQueryService;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/style")
public class DotnetMauiButtonStyleController {

    private final DotnetMauiStyleQueryService styleQueryService;

    public DotnetMauiButtonStyleController(DotnetMauiStyleQueryService styleQueryService) {
        this.styleQueryService = styleQueryService;
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
    @GetMapping("/dotnetmaui/imeterrecorder/buttons")
    public DotnetMauiButtonThemeWrapperDto getButtonsForImeterRecorder() {
        return styleQueryService.getButtonStylesForProjectSlug("imeterrecorder");
    }
}
