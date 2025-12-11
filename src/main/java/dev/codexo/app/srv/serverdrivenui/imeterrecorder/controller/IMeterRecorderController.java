package dev.codexo.app.srv.serverdrivenui.imeterrecorder.controller;

import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import dev.codexo.app.srv.serverdrivenui.imeterrecorder.model.ColorPalette;
import dev.codexo.app.srv.serverdrivenui.imeterrecorder.service.ColorPaletteService;
import dev.codexo.app.srv.serverdrivenui.imeterrecorder.service.ButtonStyleService;
import dev.codexo.app.srv.serverdrivenui.imeterrecorder.model.ButtonStyle;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/imeterrecorder")
public class IMeterRecorderController {

    private final ColorPaletteService colorPaletteService;
    private final ButtonStyleService buttonStyleService;

    public IMeterRecorderController(ColorPaletteService colorPaletteService, ButtonStyleService buttonStyleService) {
        this.colorPaletteService = colorPaletteService;
        this.buttonStyleService = buttonStyleService;
    }

    // Health check endpoint
    @GetMapping("/ping")
    public ResponseEntity<@NonNull String> ping() {
        return ResponseEntity.ok("IMeterRecorderController is alive");
    }
    
    // API info endpoint  
    @GetMapping("/info")
    public ResponseEntity<@NonNull String> info() {
        return ResponseEntity.ok("ServerDrivenUI Service - IMeter Recorder API v1.0");
    }

    // Colors endpoint returning a randomized color palette
    @GetMapping("/colors")
    public ResponseEntity<@NonNull ColorPalette> colors() {
        return ResponseEntity.ok(colorPaletteService.randomPalette());
    }

    // Button styles endpoint returning randomized button styles
    @GetMapping("/style/buttons")
    public ResponseEntity<@NonNull Map<String, List<ButtonStyle>>> buttons() {
        List<ButtonStyle> buttons = buttonStyleService.randomButtons();
        return ResponseEntity.ok(Map.of("buttons", buttons));
    }

}
