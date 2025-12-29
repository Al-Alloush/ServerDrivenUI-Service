package dev.codexo.app.srv.serverdrivenui.health;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

@Service
public class RandomButtonStyleService implements ButtonStyleService {

    private final Random random = new Random();

    @Override
    public ButtonThemesWrapperDto randomButtons() {
        ButtonThemesWrapperDto buttonThemes = new ButtonThemesWrapperDto();

        // Light theme buttons
        String lightPrimaryBg = randomHexColor();
        String lightPrimaryText = contrastText(lightPrimaryBg);
        int lightPrimaryRadius = randomCornerRadius(8, 20);

        String lightSecondaryBg = randomHexColor();
        String lightSecondaryText = contrastText(lightSecondaryBg);
        int lightSecondaryRadius = randomCornerRadius(2, 12);

        List<ButtonStylesDto> lightButtons = new ArrayList<>();
        lightButtons.add(new ButtonStylesDto("PrimaryButton", lightPrimaryBg, lightPrimaryText, lightPrimaryRadius));
        lightButtons.add(new ButtonStylesDto("SecondaryButton", lightSecondaryBg, lightSecondaryText, lightSecondaryRadius));
        buttonThemes.setLight(lightButtons);

        // Dark theme buttons
        String darkPrimaryBg = randomHexColor();
        String darkPrimaryText = contrastText(darkPrimaryBg);
        int darkPrimaryRadius = randomCornerRadius(8, 20);

        String darkSecondaryBg = randomHexColor();
        String darkSecondaryText = contrastText(darkSecondaryBg);
        int darkSecondaryRadius = randomCornerRadius(2, 12);

        List<ButtonStylesDto> darkButtons = new ArrayList<>();
        darkButtons.add(new ButtonStylesDto("PrimaryButton", darkPrimaryBg, darkPrimaryText, darkPrimaryRadius));
        darkButtons.add(new ButtonStylesDto("SecondaryButton", darkSecondaryBg, darkSecondaryText, darkSecondaryRadius));
        buttonThemes.setDark(darkButtons);

        return buttonThemes;
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

