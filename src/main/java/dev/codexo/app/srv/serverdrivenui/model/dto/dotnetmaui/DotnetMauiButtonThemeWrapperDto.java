package dev.codexo.app.srv.serverdrivenui.model.dto.dotnetmaui;

import lombok.*;

import java.util.List;
import java.util.Map;

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


    private Map<String, List<DotnetMauiButtonStyleDto>> themes;

}
