package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.border;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorderStyleUpdateDto {

    // Border Properties (matching BorderStyleDto)
    private String borderColor;
    private Integer borderWidth;
    private Integer cornerRadius;
    private String backgroundColor;
    private String strokeShape;
    private String strokeThickness;
    private String strokeDashArray;
    private String strokeDashOffset;
    private String strokeLineCap;
    private String strokeLineJoin;

    // Layout (matching BorderStyleDto)
    private String padding;
    private String margin;
    private Double heightRequest;
    private Double widthRequest;
    private Double minimumHeightRequest;
    private Double minimumWidthRequest;
    private String horizontalOptions;
    private String verticalOptions;

    // Appearance (matching BorderStyleDto)
    private Double opacity;
    private Boolean isVisible;
    private Boolean isEnabled;

    // Shadow (matching BorderStyleDto)
    private ShadowDto shadow;

    // Accessibility (matching BorderStyleDto)
    private String semanticDescription;
    private String semanticHint;

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
}

