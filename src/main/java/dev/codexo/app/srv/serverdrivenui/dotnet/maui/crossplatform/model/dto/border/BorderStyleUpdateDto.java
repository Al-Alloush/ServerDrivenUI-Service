package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.border;

import lombok.*;

/**
 * Update DTO for Border styles.
 * Property names match the exact XAML property names.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorderStyleUpdateDto {

    // Background and Stroke
    private String background;
    private String stroke;
    private Double strokeThickness;

    // Stroke Dash Pattern
    private String strokeDashArray;
    private Double strokeDashOffset;

    // Stroke Line Caps and Joins
    private String strokeLineCap;
    private String strokeLineJoin;
    private Double strokeMiterLimit;

    // Corner Radius (StrokeShape)
    private String strokeShape;

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

