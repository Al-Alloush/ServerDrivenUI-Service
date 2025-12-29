package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.entry;

import lombok.*;

import java.util.List;

/**
 * Shape of a single MAUI entry style as consumed by the client.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntryStyleDto {

    private String id;
    private String key;

    // Colors
    private String backgroundColor;
    private String textColor;
    private String placeholderColor;
    private String cursorColor;
    private String selectionHighlightColor;

    // Font Properties
    private Double fontSize;
    private String fontFamily;

    // Size and Layout
    private Double heightRequest;
    private String margin;

    // Entry Behavior
    private String clearButtonVisibility;
    private String returnType;

    // Text Alignment
    private String horizontalTextAlignment;
    private String verticalTextAlignment;

    // Additional Properties
    private String keyboard;
    private Boolean isPassword;
    private Integer maxLength;

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
        private String backgroundColor;
        private String textColor;
        private ShadowDto shadow;
    }
}
