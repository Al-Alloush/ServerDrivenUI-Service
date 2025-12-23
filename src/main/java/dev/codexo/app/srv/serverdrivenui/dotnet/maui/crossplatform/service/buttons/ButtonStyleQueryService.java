package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.service.buttons;

import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.ComponentsDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.LogosDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.ThemeDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.ThemeWrapperDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.button.ButtonShadowEntity;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.button.ButtonVisualStateEntity;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.button.ButtonVisualStateShadowEntity;
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
 * Read-only service that prepares .NET MAUI styles for a project.
 * <p>
 * Workflow:
 *  1. Load project by slug.
 *  2. Load .NET MAUI platform entity.
 *  3. Resolve ProjectPlatform relation.
 *  4. Use BrandIdentity + ButtonStyle entities to build the payload
 *     expected by the MAUI client.
 */
@Service
@Transactional(readOnly = true)
public class ButtonStyleQueryService {

    /**
     * Code stored in {@link PlatformEntity#getCode()} for .NET MAUI cross-platform.
     */


    private final PlatformThemeRepository themeRepository;
    private final ButtonStyleRepository buttonStyleRepository;

    public ButtonStyleQueryService(PlatformThemeRepository themeRepository, ButtonStyleRepository buttonStyleRepository) {

        this.themeRepository = themeRepository;
        this.buttonStyleRepository = buttonStyleRepository;
    }


    @Transactional(readOnly = true)
    public ThemeWrapperDto getButtonStylesForProjectPlatform(ProjectPlatformEntity projectPlatform) {
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

        // Build ThemeWrapperDto
        return ThemeWrapperDto.builder()
                .version(1)
                .createdDateTime(LocalDateTime.now())
                .themes(Map.of(
                        "light", buildThemeDto(lightButtons, brand, true),
                        "dark", buildThemeDto(darkButtons, brand, false)
                ))
                .logos(buildLogosDto(brand))
                .build();
    }

    private ThemeDto buildThemeDto(
            List<ButtonStyleEntity> buttons,
            BrandIdentityEntity brand,
            boolean isLight) {

        return ThemeDto.builder()
                .colors(buildColors(brand, isLight))
                .components(ComponentsDto.builder()
                        .buttons(buttons.stream()
                                .map(e -> mapToDto(e, brand))
                                .toList())
                        .labels(List.of())
                        .entries(List.of())
                        .build())
                .build();
    }


    private Map<String, String> buildColors(BrandIdentityEntity brand, boolean isLight) {
        return Map.of(
                "primary", brand.getPrimaryColor(),
                "secondary", brand.getSecondaryColor(),
                "tertiary", brand.getTertiaryColor(),
                "background", isLight ? "#FFFFFF" : "#121212",
                "text", isLight ? "#000000" : "#FFFFFF"
        );
    }

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


    private ButtonStyleDto mapToDto(ButtonStyleEntity entity, BrandIdentityEntity brand) {
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

}
