package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.border;

import lombok.*;

import java.util.List;

/**
 * DTO for Border component styles in .NET MAUI cross-platform.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BorderStyleDto {
    private String id;
    private String key;

    // Border Properties
    private String borderColor;
    private Integer borderWidth;
    private Integer cornerRadius;
    private String backgroundColor;
    private String strokeShape;       // RoundRectangle, Rectangle, etc.
    private String strokeThickness;
    private String strokeDashArray;
    private String strokeDashOffset;
    private String strokeLineCap;
    private String strokeLineJoin;

    // Layout
    private String padding;
    private String margin;
    private Double heightRequest;
    private Double widthRequest;
    private Double minimumHeightRequest;
    private Double minimumWidthRequest;
    private String horizontalOptions;
    private String verticalOptions;

    // Appearance
    private Double opacity;
    private Boolean isVisible;
    private Boolean isEnabled;

    // Shadow
    private ShadowDto shadow;

    // Accessibility
    private String semanticDescription;
    private String semanticHint;

    // Visual states
    private List<VisualStateDto> visualStates;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ShadowDto {
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
    public static class VisualStateDto {
        private String name;
        private Double opacity;
        private String borderColor;
        private String backgroundColor;
        private ShadowDto shadow;
    }
}
