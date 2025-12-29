package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.button;

import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ButtonStyleUpdateDto {
    private String key;

    // Appearance
    private String text;
    private String textColor;
    private String backgroundColor;
    private Double opacity;
    private Boolean isVisible;
    private Boolean isEnabled;

    // Typography
    private String fontFamily;
    private Double fontSize;
    private String fontAttributes;
    private Double characterSpacing;
    private String lineBreakMode;
    private String textTransform;

    // Layout
    private String padding;
    private String margin;
    private Double heightRequest;
    private Double widthRequest;
    private Double minimumHeightRequest;
    private Double minimumWidthRequest;
    private String horizontalOptions;
    private String verticalOptions;
    private String contentLayout;

    // Border
    private String borderColor;
    private Integer borderWidth;
    private Integer cornerRadius;

    // Image
    private String imageSource;

    // Shadow (can be null to remove)
    private ShadowUpdateDto shadow;

    // Accessibility
    private String semanticDescription;
    private String semanticHint;

    // Visual States (can be empty to remove all)
    private List<VisualStateUpdateDto> visualStates;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ShadowUpdateDto {
        private String shadowBrush;
        private Float shadowOpacity;
        private Float shadowRadius;
        private String shadowOffset;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VisualStateUpdateDto {
        private String name;
        private Double opacity;
        private String textColor;
        private String backgroundColor;
        private String borderColor;
        private ShadowUpdateDto shadow;
    }
}
