package dev.codexo.app.srv.serverdrivenui.model.dto.dotnetmaui;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DotnetMauiButtonStyleUpdateDto {
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

    // Accessibility
    private String semanticDescription;
    private String semanticHint;
}
