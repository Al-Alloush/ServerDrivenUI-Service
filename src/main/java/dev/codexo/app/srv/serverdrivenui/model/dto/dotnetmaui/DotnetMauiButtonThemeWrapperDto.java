package dev.codexo.app.srv.serverdrivenui.model.dto.dotnetmaui;

import lombok.*;

import java.util.List;

/**
 * Container for light / dark theme button styles.
 * <p>
 * This matches what your MAUI client expects:
 * <p>
 * {
 *   "light": [ ... ],
 *   "dark":  [ ... ]
 * }
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DotnetMauiButtonThemeWrapperDto {

    /**
     * Styles for light theme.
     */
    private List<DotnetMauiButtonStyleDto> light;

    /**
     * Styles for dark theme.
     * For the demo we reuse the same styles as light.
     * Later you can store dedicated dark variants.
     */
    private List<DotnetMauiButtonStyleDto> dark;
}
