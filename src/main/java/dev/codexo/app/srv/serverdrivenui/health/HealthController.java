package dev.codexo.app.srv.serverdrivenui.health;

import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/health")
public class HealthController {

    private final ColorPaletteService colorPaletteService;
    private final ButtonStyleService buttonStyleService;

    public HealthController(ColorPaletteService colorPaletteService, ButtonStyleService buttonStyleService) {
        this.colorPaletteService = colorPaletteService;
        this.buttonStyleService = buttonStyleService;
    }

    // Health check endpoint
    @GetMapping("/ping")
    public ResponseEntity<@NonNull String> ping() {
        return ResponseEntity.ok("Health controller is alive");
    }
    
    // API info endpoint  
    @GetMapping("/info")
    public ResponseEntity<@NonNull String> info() {
        return ResponseEntity.ok("ServerDrivenUI Service - IMeter Recorder API v1.0");
    }

    // Colors endpoint returning a randomized color palette
    @GetMapping("/colors")
    public ResponseEntity<@NonNull ColorThemeWrapperDto> colors() {
        return ResponseEntity.ok(colorPaletteService.randomPalette());
    }

    // Button styles endpoint returning randomized button styles
    @GetMapping("/style/buttons")
    public ResponseEntity<@NonNull ButtonThemesWrapperDto> buttons() {
        ButtonThemesWrapperDto buttons = buttonStyleService.randomButtons();
        return ResponseEntity.ok(buttons);
    }

}
