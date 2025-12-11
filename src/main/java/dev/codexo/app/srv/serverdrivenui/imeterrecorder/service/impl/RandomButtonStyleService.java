package dev.codexo.app.srv.serverdrivenui.imeterrecorder.service.impl;

import dev.codexo.app.srv.serverdrivenui.imeterrecorder.model.ButtonStyle;
import dev.codexo.app.srv.serverdrivenui.imeterrecorder.service.ButtonStyleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

@Service
public class RandomButtonStyleService implements ButtonStyleService {

    private final Random random = new Random();

    @Override
    public List<ButtonStyle> randomButtons() {
        List<ButtonStyle> buttons = new ArrayList<>();

        // Primary button
        String primaryBg = randomHexColor();
        String primaryText = contrastText(primaryBg);
        int primaryRadius = randomCornerRadius(8, 20); // bias to larger radius
        buttons.add(new ButtonStyle("PrimaryButton", primaryBg, primaryText, primaryRadius));

        // Secondary button
        String secondaryBg = randomHexColor();
        String secondaryText = contrastText(secondaryBg);
        int secondaryRadius = randomCornerRadius(2, 12);
        buttons.add(new ButtonStyle("SecondaryButton", secondaryBg, secondaryText, secondaryRadius));

        return buttons;
    }

    private String randomHexColor() {
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return String.format("#%02X%02X%02X", r, g, b);
    }

    private String contrastText(String hex) {
        int r = Integer.parseInt(hex.substring(1, 3), 16);
        int g = Integer.parseInt(hex.substring(3, 5), 16);
        int b = Integer.parseInt(hex.substring(5, 7), 16);
        double luminance = (0.2126 * r + 0.7152 * g + 0.0722 * b) / 255;
        return luminance > 0.5 ? "#000000" : "#FFFFFF";
    }

    private int randomCornerRadius(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
}

