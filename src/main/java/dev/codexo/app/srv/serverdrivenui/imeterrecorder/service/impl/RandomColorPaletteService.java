package dev.codexo.app.srv.serverdrivenui.imeterrecorder.service.impl;

import dev.codexo.app.srv.serverdrivenui.imeterrecorder.model.ColorThemeWrapperDto;
import dev.codexo.app.srv.serverdrivenui.imeterrecorder.model.ThemePalette;
import org.springframework.stereotype.Service;

import dev.codexo.app.srv.serverdrivenui.imeterrecorder.service.ColorPaletteService;

import java.util.List;
import java.util.Random;

@Service
public class RandomColorPaletteService implements ColorPaletteService {

    private final Random random = new Random();

    @Override
    public ColorThemeWrapperDto randomPalette() {
        // The user requested initial specific values; but they also asked subsequent calls change randomly.
        // We'll generate colors but bias towards visible palettes. Also include possibility to return fixed example.

        var dto = new ColorThemeWrapperDto();

        String primary = randomHexColor();
        String primaryDark = lightenOrDarken(primary, -40);
        String primaryDarkText = randomTextContrast(primary);
        String secondary = randomHexColor();
        String secondaryDark = randomHexColor();
        String secondaryDarkText = randomTextContrast(secondary);
        String tertiary = randomHexColor();
        var light = new ThemePalette(primary, primaryDark, primaryDarkText, secondary,secondaryDark, secondaryDarkText, tertiary);
        dto.setLight(light);

        primary = randomHexColor();
        primaryDark = lightenOrDarken(primary, -40);
        primaryDarkText = randomTextContrast(primary);
        secondary = randomHexColor();
        secondaryDark = randomHexColor();
        secondaryDarkText = randomTextContrast(secondary);
        tertiary = randomHexColor();
        var dark = new ThemePalette(primary, primaryDark, primaryDarkText, secondary,secondaryDark, secondaryDarkText, tertiary);
        dto.setDark(dark);
        return dto;
    }

    private String randomHexColor() {
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return String.format("#%02X%02X%02X", r, g, b);
    }

    private String lightenOrDarken(String hex, int amount) {
        // amount in range -255..255
        int r = Integer.parseInt(hex.substring(1, 3), 16);
        int g = Integer.parseInt(hex.substring(3, 5), 16);
        int b = Integer.parseInt(hex.substring(5, 7), 16);
        r = clamp(r + amount, 0, 255);
        g = clamp(g + amount, 0, 255);
        b = clamp(b + amount, 0, 255);
        return String.format("#%02X%02X%02X", r, g, b);
    }

    private int clamp(int v, int min, int max) {
        return Math.max(min, Math.min(max, v));
    }

    private String randomTextContrast(String hex) {
        // Return either #000000 or #FFFFFF depending on luminance of hex
        int r = Integer.parseInt(hex.substring(1, 3), 16);
        int g = Integer.parseInt(hex.substring(3, 5), 16);
        int b = Integer.parseInt(hex.substring(5, 7), 16);
        double luminance = (0.2126 * r + 0.7152 * g + 0.0722 * b) / 255;
        return luminance > 0.5 ? "#242424" : "#FFFFFF";
    }
}

