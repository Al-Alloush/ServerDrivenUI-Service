package dev.codexo.app.srv.serverdrivenui.model.dto.dotnetmaui;

import lombok.*;

/**
 * Shape of a single MAUI button style as consumed by the client.
 * <p>
 * JSON example:
 * {
 *   "key": "PrimaryButton",
 *   "background": "#26c998",
 *   "textColor": "#ffffff",
 *   "borderColor": "#26c998",
 *   "cornerRadius": 8,
 *   "borderWidth": 1
 * }
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DotnetMauiButtonStyleDto {

    /**
     * Logical style key used by the MAUI client
     * (e.g. "PrimaryButton", "SecondaryButton").
     */
    private String key;

    /**
     * Resolved background color in hex (#RRGGBB or #AARRGGBB).
     * Tokens like PRIMARY / SECONDARY are already resolved on the server.
     */
    private String background;

    /**
     * Resolved text (foreground) color in hex.
     */
    private String textColor;

    /**
     * Optional border color in hex.
     */
    private String borderColor;

    /**
     * Corner radius in DIPs (device-independent pixels).
     */
    private Integer cornerRadius;

    /**
     * Border width in DIPs.
     */
    private Integer borderWidth;
}
