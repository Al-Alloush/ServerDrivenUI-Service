package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.label;


import lombok.*;

import java.util.List;

/**
 * DTO for updating MAUI label style properties.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LabelStyleUpdateDto {

    // ...existing properties...
    // Text Content
    private String text;
    private String textColor;
    private String formattedText;

    // Font Properties
    private String fontFamily;
    private Double fontSize;
    private String fontAttributes;
    private Boolean fontAutoScalingEnabled;

    // Text Layout
    private String textTransform;
    private Double characterSpacing;
    private String lineBreakMode;
    private Integer maxLines;
    private Double lineHeight;

    // Text Alignment
    private String horizontalTextAlignment;
    private String verticalTextAlignment;

    // Text Decoration
    private String textDecorations;

    // Padding
    private String padding;

    // Size
    private Double heightRequest;
    private Double widthRequest;
    private Double minimumHeightRequest;
    private Double minimumWidthRequest;
    private Double maximumHeightRequest;
    private Double maximumWidthRequest;

    // Layout
    private String horizontalOptions;
    private String verticalOptions;
    private String margin;

    // Background
    private String backgroundColor;

    // Visibility and Interaction
    private Boolean isVisible;
    private Boolean isEnabled;
    private Double opacity;
    private Boolean inputTransparent;

    // Transforms
    private Double anchorX;
    private Double anchorY;
    private Double rotation;
    private Double rotationX;
    private Double rotationY;
    private Double scale;
    private Double scaleX;
    private Double scaleY;
    private Double translationX;
    private Double translationY;

    // Z-Index
    private Integer zIndex;

    // Flow Direction
    private String flowDirection;

    // Semantics
    private String automationId;

    // Shadow
    private ShadowDto shadow;

    // Visual States
    private List<VisualStateDto> visualStates;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ShadowDto {
        private String shadowBrush;
        private Double shadowOpacity;
        private Double shadowRadius;
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
        private String textColor;
        private String fontFamily;
        private String fontAttributes;
        private ShadowDto shadow;
    }
}


