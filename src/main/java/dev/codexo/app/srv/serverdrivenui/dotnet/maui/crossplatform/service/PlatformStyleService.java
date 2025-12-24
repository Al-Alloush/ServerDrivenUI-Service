package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.service;

import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.ComponentsDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.LogosDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.ThemeDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.ThemeWrapperDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.border.BorderStyleDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.border.BorderStyleEntity;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.border.BorderVisualStateEntity;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.button.ButtonShadowEntity;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.button.ButtonVisualStateEntity;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.button.ButtonVisualStateShadowEntity;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.repository.BorderStyleRepository;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.repository.ButtonStyleRepository;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.button.ButtonStyleDto;
import dev.codexo.app.srv.serverdrivenui.model.entity.*;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.button.ButtonStyleEntity;
import dev.codexo.app.srv.serverdrivenui.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Service that prepares .NET MAUI styles for a project.
 * <p>
 * Provides separate functions to build different component types:
 * - Buttons
 * - Colors
 * - Labels
 * - Entries
 * - Borders
 */
@Service
@Transactional(readOnly = true)
public class PlatformStyleService {

    private final PlatformThemeRepository themeRepository;
    private final ButtonStyleRepository buttonStyleRepository;
    private final BorderStyleRepository borderStyleRepository;

    public PlatformStyleService(PlatformThemeRepository themeRepository,
                                ButtonStyleRepository buttonStyleRepository,
                                BorderStyleRepository borderStyleRepository) {
        this.themeRepository = themeRepository;
        this.buttonStyleRepository = buttonStyleRepository;
        this.borderStyleRepository = borderStyleRepository;
    }

    /**
     * Main entry point to get all platform styles for a project-platform.
     * Coordinates the building of all component types.
     */
    @Transactional(readOnly = true)
    public ThemeWrapperDto getPlatformStyle(ProjectPlatformEntity projectPlatform) {
        BrandIdentityEntity brand = projectPlatform.getProject().getBrandIdentity();
        if (brand == null) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Project does not have a BrandIdentity configured.");
        }

        // Get themes for this specific project-platform
        List<PlatformThemeEntity> themes = themeRepository.findByProjectPlatform(projectPlatform);

        // Find light and dark themes
        PlatformThemeEntity lightTheme = themes.stream()
                .filter(t -> "Light".equalsIgnoreCase(t.getThemeName()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Light theme not found for project " + projectPlatform.getProject().getSlug()));

        PlatformThemeEntity darkTheme = themes.stream()
                .filter(t -> "Dark".equalsIgnoreCase(t.getThemeName()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Dark theme not found for project " + projectPlatform.getProject().getSlug()));

        // Get button styles for each theme
        List<ButtonStyleEntity> lightButtons = buttonStyleRepository.findByTheme(lightTheme);
        List<ButtonStyleEntity> darkButtons = buttonStyleRepository.findByTheme(darkTheme);

        // Get border styles for each theme
        List<BorderStyleEntity> lightBorders = borderStyleRepository.findByTheme(lightTheme);
        List<BorderStyleEntity> darkBorders = borderStyleRepository.findByTheme(darkTheme);

        // Build ThemeWrapperDto
        return ThemeWrapperDto.builder()
                .version(1)
                .createdDateTime(LocalDateTime.now())
                .themes(Map.of(
                        "light", buildThemeDto(lightButtons, lightBorders, brand, true),
                        "dark", buildThemeDto(darkButtons, darkBorders, brand, false)
                ))
                .logos(buildLogosDto(brand))
                .build();
    }



    /**
     * Builds a ThemeDto with all components for a specific theme.
     */
    private ThemeDto buildThemeDto(
            List<ButtonStyleEntity> buttons,
            List<BorderStyleEntity> borders,
            BrandIdentityEntity brand,
            boolean isLight) {

        return ThemeDto.builder()
                .colors(buildColors(brand, isLight))
                .components(ComponentsDto.builder()
                        .buttons(buildButtons(buttons, brand))
                        .labels(buildLabels())
                        .entries(buildEntries())
                        .borders(buildBorders(borders))
                        .build())
                .build();
    }

    /**
     * Builds the color palette map for a theme.
     *
     * @param brand The brand identity containing color information
     * @param isLight Whether this is for a light theme
     * @return Map of color names to hex values
     */
    public Map<String, String> buildColors(BrandIdentityEntity brand, boolean isLight) {
        return Map.of(
                "primary", brand.getPrimaryColor(),
                "secondary", brand.getSecondaryColor(),
                "tertiary", brand.getTertiaryColor(),
                "background", isLight ? "#FFFFFF" : "#121212",
                "text", isLight ? "#000000" : "#FFFFFF"
        );
    }

    /**
     * Builds the list of button styles from entities.
     *
     * @param buttonEntities List of button style entities
     * @param brand The brand identity for fallback values
     * @return List of ButtonStyleDto
     */
    public List<ButtonStyleDto> buildButtons(List<ButtonStyleEntity> buttonEntities, BrandIdentityEntity brand) {
        return buttonEntities.stream()
                .map(entity -> mapButtonToDto(entity, brand))
                .toList();
    }

    /**
     * Builds the list of label styles.
     * TODO: Implement when LabelStyleEntity is available
     *
     * @return Empty list for now
     */
    public List<Object> buildLabels() {
        // TODO: Implement label style mapping
        return List.of();
    }

    /**
     * Builds the list of entry (input field) styles.
     * TODO: Implement when EntryStyleEntity is available
     *
     * @return Empty list for now
     */
    public List<Object> buildEntries() {
        // TODO: Implement entry style mapping
        return List.of();
    }

    /**
     * Builds the list of border styles from entities.
     *
     * @param borderEntities List of border style entities
     * @return List of BorderStyleDto
     */
    public List<BorderStyleDto> buildBorders(List<BorderStyleEntity> borderEntities) {
        return borderEntities.stream()
                .map(this::mapBorderToDto)
                .toList();
    }

    /**
     * Builds logos DTO for both light and dark themes.
     */
    private Map<String, LogosDto> buildLogosDto(BrandIdentityEntity brand) {
        return Map.of(
                "light", LogosDto.builder()
                        .primary(brand.getLogoUrl())
                        .secondary(brand.getLogoUrl())
                        .build(),
                "dark", LogosDto.builder()
                        .primary(brand.getLogoUrl())
                        .secondary(brand.getLogoUrl())
                        .build()
        );
    }

    /**
     * Maps a ButtonStyleEntity to ButtonStyleDto.
     */
    private ButtonStyleDto mapButtonToDto(ButtonStyleEntity entity, BrandIdentityEntity brand) {
        return ButtonStyleDto.builder()
                .id(entity.getId())
                .key(entity.getStyleKey())
                // Appearance
                .text(entity.getAppearance() != null ? entity.getAppearance().getText() : null)
                .textColor(entity.getAppearance() != null ? entity.getAppearance().getTextColor() : null)
                .backgroundColor(entity.getAppearance() != null ? entity.getAppearance().getBackgroundColor() : null)
                .opacity(entity.getAppearance() != null ? entity.getAppearance().getOpacity() : null)
                .isVisible(entity.getAppearance() != null ? entity.getAppearance().isVisible() : null)
                .isEnabled(entity.getAppearance() != null ? entity.getAppearance().isEnabled() : null)
                // Typography
                .fontFamily(entity.getTypography() != null ? entity.getTypography().getFontFamily() : brand.getFontFamily())
                .fontSize(entity.getTypography() != null ? entity.getTypography().getFontSize() : null)
                .fontAttributes(entity.getTypography() != null ? entity.getTypography().getFontAttributes() : null)
                .characterSpacing(entity.getTypography() != null ? entity.getTypography().getCharacterSpacing() : null)
                .lineBreakMode(entity.getTypography() != null ? entity.getTypography().getLineBreakMode() : null)
                .textTransform(entity.getTypography() != null ? entity.getTypography().getTextTransform() : null)
                // Layout
                .padding(entity.getLayout() != null ? entity.getLayout().getPadding() : null)
                .margin(entity.getLayout() != null ? entity.getLayout().getMargin() : null)
                .heightRequest(entity.getLayout() != null ? entity.getLayout().getHeightRequest() : null)
                .widthRequest(entity.getLayout() != null ? entity.getLayout().getWidthRequest() : null)
                .minimumHeightRequest(entity.getLayout() != null ? entity.getLayout().getMinimumHeightRequest() : null)
                .minimumWidthRequest(entity.getLayout() != null ? entity.getLayout().getMinimumWidthRequest() : null)
                .horizontalOptions(entity.getLayout() != null ? entity.getLayout().getHorizontalOptions() : null)
                .verticalOptions(entity.getLayout() != null ? entity.getLayout().getVerticalOptions() : null)
                .contentLayout(entity.getLayout() != null ? entity.getLayout().getContentLayout() : null)
                // Border
                .borderColor(entity.getBorder() != null ? entity.getBorder().getBorderColor() : null)
                .borderWidth(entity.getBorder() != null ? entity.getBorder().getBorderWidth() : null)
                .cornerRadius(entity.getBorder() != null ? entity.getBorder().getCornerRadius() : null)
                // Image
                .imageSource(entity.getImageSource())
                // Shadow
                .shadow(mapShadowToDto(entity.getShadow()))
                // Accessibility
                .semanticDescription(entity.getAccessibility() != null ? entity.getAccessibility().getSemanticDescription() : null)
                .semanticHint(entity.getAccessibility() != null ? entity.getAccessibility().getSemanticHint() : null)
                // Visual States
                .visualStates(entity.getVisualStates() != null ?
                        entity.getVisualStates().stream()
                                .map(this::mapVisualStateToDto)
                                .toList() : List.of())
                .build();
    }

    private ButtonStyleDto.ShadowDto mapShadowToDto(ButtonShadowEntity shadow) {
        if (shadow == null) {
            return null;
        }
        return ButtonStyleDto.ShadowDto.builder()
                .shadowBrush(shadow.getShadowBrush())
                .shadowOpacity(shadow.getShadowOpacity())
                .shadowRadius(shadow.getShadowRadius())
                .shadowOffset(shadow.getShadowOffsetX())
                .build();
    }

    private ButtonStyleDto.VisualStateDto mapVisualStateToDto(ButtonVisualStateEntity state) {
        return ButtonStyleDto.VisualStateDto.builder()
                .name(state.getName())
                .opacity(state.getOpacity())
                .textColor(state.getTextColor())
                .backgroundColor(state.getBackgroundColor())
                .borderColor(state.getBorderColor())
                .shadow(mapVisualStateShadowToDto(state.getShadow()))
                .build();
    }

    private ButtonStyleDto.ShadowDto mapVisualStateShadowToDto(ButtonVisualStateShadowEntity shadow) {
        if (shadow == null) {
            return null;
        }
        return ButtonStyleDto.ShadowDto.builder()
                .shadowBrush(shadow.getShadowBrush())
                .shadowOpacity(shadow.getShadowOpacity())
                .shadowRadius(shadow.getShadowRadius())
                .shadowOffset(shadow.getShadowOffsetX())
                .build();
    }

    /**
     * Maps a BorderStyleEntity to BorderStyleDto.
     */
    private BorderStyleDto mapBorderToDto(BorderStyleEntity entity) {
        return BorderStyleDto.builder()
                .id(entity.getId())
                .key(entity.getStyleKey())
                // Border Properties - Fixed mappings
                .borderColor(entity.getStroke())  // stroke -> borderColor
                .borderWidth(entity.getStrokeThickness() != null ? entity.getStrokeThickness().intValue() : null)
                .backgroundColor(entity.getBackground())
                .strokeShape(entity.getStrokeShape())
                .strokeThickness(entity.getStrokeThickness() != null ? entity.getStrokeThickness().toString() : null)
                .strokeDashArray(entity.getStrokeDashArray())
                .strokeDashOffset(entity.getStrokeDashOffset() != null ? entity.getStrokeDashOffset().toString() : null)
                .strokeLineCap(entity.getStrokeLineCap() != null ? entity.getStrokeLineCap().name() : null)
                .strokeLineJoin(entity.getStrokeLineJoin() != null ? entity.getStrokeLineJoin().name() : null)
                // Layout
                .padding(entity.getPadding())
                .margin(entity.getMargin())
                .heightRequest(entity.getHeightRequest())
                .widthRequest(entity.getWidthRequest())
                .minimumHeightRequest(entity.getMinimumHeightRequest())
                .minimumWidthRequest(entity.getMinimumWidthRequest())
                .horizontalOptions(entity.getHorizontalOptions() != null ? entity.getHorizontalOptions().name() : null)
                .verticalOptions(entity.getVerticalOptions() != null ? entity.getVerticalOptions().name() : null)
                // Appearance
                .opacity(entity.getOpacity())
                .isVisible(entity.getIsVisible())
                .isEnabled(entity.getIsEnabled())
                // Shadow
                .shadow(null)
                // Accessibility
                .semanticDescription(entity.getAutomationId())
                .semanticHint(null)
                // Visual States
                .visualStates(entity.getVisualStates() != null ?
                        entity.getVisualStates().stream()
                                .map(this::mapBorderVisualStateToDto)
                                .toList() : List.of())
                .build();
    }


    /**
     * Maps a BorderVisualStateEntity to BorderStyleDto.VisualStateDto.
     */
    private BorderStyleDto.VisualStateDto mapBorderVisualStateToDto(BorderVisualStateEntity state) {
        return BorderStyleDto.VisualStateDto.builder()
                .name(state.getName())
                .opacity(state.getOpacity())
                .borderColor(null) // Not available in current entity
                .backgroundColor(null) // Not available in current entity
                .shadow(null) // Not available in current entity
                .build();
    }

}
