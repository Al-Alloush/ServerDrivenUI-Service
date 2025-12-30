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
    private final dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.repository.LabelStyleRepository labelStyleRepository;
    private final dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.repository.EntryStyleRepository entryStyleRepository;

    public PlatformStyleService(PlatformThemeRepository themeRepository,
                                ButtonStyleRepository buttonStyleRepository,
                                BorderStyleRepository borderStyleRepository,
                                dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.repository.LabelStyleRepository labelStyleRepository,
                                dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.repository.EntryStyleRepository entryStyleRepository) {
        this.themeRepository = themeRepository;
        this.buttonStyleRepository = buttonStyleRepository;
        this.borderStyleRepository = borderStyleRepository;
        this.labelStyleRepository = labelStyleRepository;
        this.entryStyleRepository = entryStyleRepository;
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

        // Get label styles for each theme
        List<dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.label.LabelStyleEntity> lightLabels = labelStyleRepository.findByThemeId(lightTheme.getId());
        List<dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.label.LabelStyleEntity> darkLabels = labelStyleRepository.findByThemeId(darkTheme.getId());

        // Get entry styles for each theme
        List<dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.entry.EntryStyleEntity> lightEntries = entryStyleRepository.findByThemeId(lightTheme.getId());
        List<dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.entry.EntryStyleEntity> darkEntries = entryStyleRepository.findByThemeId(darkTheme.getId());

        // Build ThemeWrapperDto
        return ThemeWrapperDto.builder()
                .version(1)
                .createdDateTime(LocalDateTime.now())
                .themes(Map.of(
                        "light", buildThemeDto(lightButtons, lightBorders, lightLabels, lightEntries, brand, true),
                        "dark", buildThemeDto(darkButtons, darkBorders, darkLabels, darkEntries, brand, false)
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
            List<dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.label.LabelStyleEntity> labels,
            List<dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.entry.EntryStyleEntity> entries,
            BrandIdentityEntity brand,
            boolean isLight) {

        return ThemeDto.builder()
                .colors(buildColors(brand, isLight))
                .components(ComponentsDto.builder()
                        .buttons(buildButtons(buttons, brand))
                        .labels(buildLabels(labels, brand))
                        .entries(buildEntries(entries, brand))
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
     * Builds the list of label styles from entities.
     *
     * @param labelEntities List of label style entities
     * @param brand The brand identity for fallback values
     * @return List of LabelStyleDto
     */
    public List<dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.label.LabelStyleDto> buildLabels(
            List<dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.label.LabelStyleEntity> labelEntities,
            BrandIdentityEntity brand) {
        return labelEntities.stream()
                .map(entity -> mapLabelToDto(entity, brand))
                .toList();
    }

    /**
     * Builds the list of entry styles from entities.
     *
     * @param entryEntities List of entry style entities
     * @param brand The brand identity for fallback values
     * @return List of EntryStyleDto
     */
    public List<dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.entry.EntryStyleDto> buildEntries(
            List<dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.entry.EntryStyleEntity> entryEntities,
            BrandIdentityEntity brand) {
        return entryEntities.stream()
                .map(entity -> mapEntryToDto(entity, brand))
                .toList();
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
                // Background and Stroke
                .background(entity.getBackground())
                .stroke(entity.getStroke())
                .strokeThickness(entity.getStrokeThickness())
                // Stroke Dash Pattern
                .strokeDashArray(entity.getStrokeDashArray())
                .strokeDashOffset(entity.getStrokeDashOffset())
                // Stroke Line Caps and Joins
                .strokeLineCap(entity.getStrokeLineCap() != null ? entity.getStrokeLineCap().name() : null)
                .strokeLineJoin(entity.getStrokeLineJoin() != null ? entity.getStrokeLineJoin().name() : null)
                .strokeMiterLimit(entity.getStrokeMiterLimit())
                // Corner Radius (StrokeShape)
                .strokeShape(entity.getStrokeShape())
                // Padding
                .padding(entity.getPadding())
                // Size
                .heightRequest(entity.getHeightRequest())
                .widthRequest(entity.getWidthRequest())
                .minimumHeightRequest(entity.getMinimumHeightRequest())
                .minimumWidthRequest(entity.getMinimumWidthRequest())
                .maximumHeightRequest(entity.getMaximumHeightRequest())
                .maximumWidthRequest(entity.getMaximumWidthRequest())
                // Layout
                .horizontalOptions(entity.getHorizontalOptions() != null ? entity.getHorizontalOptions().name() : null)
                .verticalOptions(entity.getVerticalOptions() != null ? entity.getVerticalOptions().name() : null)
                .margin(entity.getMargin())
                // Visibility and Interaction
                .isVisible(entity.getIsVisible())
                .isEnabled(entity.getIsEnabled())
                .opacity(entity.getOpacity())
                .inputTransparent(entity.getInputTransparent())
                // Transforms
                .anchorX(entity.getAnchorX())
                .anchorY(entity.getAnchorY())
                .rotation(entity.getRotation())
                .rotationX(entity.getRotationX())
                .rotationY(entity.getRotationY())
                .scale(entity.getScale())
                .scaleX(entity.getScaleX())
                .scaleY(entity.getScaleY())
                .translationX(entity.getTranslationX())
                .translationY(entity.getTranslationY())
                // Z-Index
                .zIndex(entity.getZIndex())
                // Flow Direction
                .flowDirection(entity.getFlowDirection() != null ? entity.getFlowDirection().name() : null)
                // Semantics
                .automationId(entity.getAutomationId())
                // Shadow
                .shadow(mapBorderShadowToDto(entity.getShadow()))
                // Visual States
                .visualStates(entity.getVisualStates() != null ?
                        entity.getVisualStates().stream()
                                .map(this::mapBorderVisualStateToDto)
                                .toList() : List.of())
                .build();
    }

    private BorderStyleDto.ShadowDto mapBorderShadowToDto(dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.border.BorderShadowEntity shadow) {
        if (shadow == null) {
            return null;
        }
        return BorderStyleDto.ShadowDto.builder()
                .shadowBrush(shadow.getShadowBrush())
                .shadowOpacity(shadow.getShadowOpacity())
                .shadowRadius(shadow.getShadowRadius())
                .shadowOffset(shadow.getShadowOffset())
                .build();
    }

    /**
     * Maps a BorderVisualStateEntity to BorderStyleDto.VisualStateDto.
     */
    private BorderStyleDto.VisualStateDto mapBorderVisualStateToDto(BorderVisualStateEntity state) {
        return BorderStyleDto.VisualStateDto.builder()
                .name(state.getName())
                .opacity(state.getOpacity())
                .build();
    }

    /**
     * Maps a LabelStyleEntity to LabelStyleDto.
     */
    private dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.label.LabelStyleDto mapLabelToDto(
            dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.label.LabelStyleEntity entity,
            BrandIdentityEntity brand) {
        return dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.label.LabelStyleDto.builder()
                .id(entity.getId())
                .key(entity.getKey())
                // Text Content
                .text(entity.getText())
                .textColor(entity.getTextColor())
                .formattedText(entity.getFormattedText())
                // Font Properties
                .fontFamily(entity.getFontFamily() != null ? entity.getFontFamily() : brand.getFontFamily())
                .fontSize(entity.getFontSize())
                .fontAttributes(entity.getFontAttributes() != null ? entity.getFontAttributes().name() : null)
                .fontAutoScalingEnabled(entity.getFontAutoScalingEnabled())
                // Text Layout
                .textTransform(entity.getTextTransform() != null ? entity.getTextTransform().name() : null)
                .characterSpacing(entity.getCharacterSpacing())
                .lineBreakMode(entity.getLineBreakMode() != null ? entity.getLineBreakMode().name() : null)
                .maxLines(entity.getMaxLines())
                .lineHeight(entity.getLineHeight())
                // Text Alignment
                .horizontalTextAlignment(entity.getHorizontalTextAlignment() != null ? entity.getHorizontalTextAlignment().name() : null)
                .verticalTextAlignment(entity.getVerticalTextAlignment() != null ? entity.getVerticalTextAlignment().name() : null)
                // Text Decoration
                .textDecorations(entity.getTextDecorations() != null ? entity.getTextDecorations().name() : null)
                // Padding
                .padding(entity.getPadding())
                // Size
                .heightRequest(entity.getHeightRequest())
                .widthRequest(entity.getWidthRequest())
                .minimumHeightRequest(entity.getMinimumHeightRequest())
                .minimumWidthRequest(entity.getMinimumWidthRequest())
                .maximumHeightRequest(entity.getMaximumHeightRequest())
                .maximumWidthRequest(entity.getMaximumWidthRequest())
                // Layout
                .horizontalOptions(entity.getHorizontalOptions() != null ? entity.getHorizontalOptions().name() : null)
                .verticalOptions(entity.getVerticalOptions() != null ? entity.getVerticalOptions().name() : null)
                .margin(entity.getMargin())
                // Background
                .backgroundColor(entity.getBackgroundColor())
                // Visibility and Interaction
                .isVisible(entity.getIsVisible())
                .isEnabled(entity.getIsEnabled())
                .opacity(entity.getOpacity())
                .inputTransparent(entity.getInputTransparent())
                // Transforms
                .anchorX(entity.getAnchorX())
                .anchorY(entity.getAnchorY())
                .rotation(entity.getRotation())
                .rotationX(entity.getRotationX())
                .rotationY(entity.getRotationY())
                .scale(entity.getScale())
                .scaleX(entity.getScaleX())
                .scaleY(entity.getScaleY())
                .translationX(entity.getTranslationX())
                .translationY(entity.getTranslationY())
                // Z-Index
                .zIndex(entity.getZIndex())
                // Flow Direction
                .flowDirection(entity.getFlowDirection() != null ? entity.getFlowDirection().name() : null)
                // Semantics
                .automationId(entity.getAutomationId())
                // Shadow
                .shadow(mapLabelShadowToDto(entity.getShadow()))
                // Visual States
                .visualStates(entity.getVisualStates() != null ?
                        entity.getVisualStates().stream()
                                .map(this::mapLabelVisualStateToDto)
                                .toList() : List.of())
                .build();
    }

    private dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.label.LabelStyleDto.ShadowDto mapLabelShadowToDto(
            dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.label.LabelShadowEntity shadow) {
        if (shadow == null) {
            return null;
        }
        return dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.label.LabelStyleDto.ShadowDto.builder()
                .shadowBrush(shadow.getShadowBrush())
                .shadowOpacity(shadow.getShadowOpacity())
                .shadowRadius(shadow.getShadowRadius())
                .shadowOffset(shadow.getShadowOffset())
                .build();
    }

    private dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.label.LabelStyleDto.VisualStateDto mapLabelVisualStateToDto(
            dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.label.LabelVisualStateEntity state) {
        return dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.label.LabelStyleDto.VisualStateDto.builder()
                .name(state.getStateName())
                .opacity(state.getOpacity())
                .textColor(state.getTextColor())
                .fontFamily(state.getFontFamily())
                .fontAttributes(state.getFontAttributes() != null ? state.getFontAttributes().name() : null)
                .shadow(mapLabelVisualStateShadowToDto(state.getShadow()))
                .build();
    }

    private dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.label.LabelStyleDto.ShadowDto mapLabelVisualStateShadowToDto(
            dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.label.LabelVisualStateShadowEntity shadow) {
        if (shadow == null) {
            return null;
        }
        return dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.label.LabelStyleDto.ShadowDto.builder()
                .shadowBrush(shadow.getShadowBrush())
                .shadowOpacity(shadow.getShadowOpacity())
                .shadowRadius(shadow.getShadowRadius())
                .shadowOffset(shadow.getShadowOffset())
                .build();
    }

    /**
     * Maps an EntryStyleEntity to EntryStyleDto.
     */
    private dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.entry.EntryStyleDto mapEntryToDto(
            dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.entry.EntryStyleEntity entity,
            BrandIdentityEntity brand) {
        return dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.entry.EntryStyleDto.builder()
                .id(entity.getId())
                .key(entity.getStyleKey())
                // Colors
                .backgroundColor(entity.getBackgroundColor())
                .textColor(entity.getTextColor())
                .placeholderColor(entity.getPlaceholderColor())
                .cursorColor(entity.getCursorColor())
                .selectionHighlightColor(entity.getSelectionHighlightColor())
                // Font Properties
                .fontSize(entity.getFontSize())
                .fontFamily(entity.getFontFamily() != null ? entity.getFontFamily() : brand.getFontFamily())
                // Size and Layout
                .heightRequest(entity.getHeightRequest())
                .margin(entity.getMargin())
                // Entry Behavior
                .clearButtonVisibility(entity.getClearButtonVisibility() != null ? entity.getClearButtonVisibility().name() : null)
                .returnType(entity.getReturnType() != null ? entity.getReturnType().name() : null)
                // Text Alignment
                .horizontalTextAlignment(entity.getHorizontalTextAlignment() != null ? entity.getHorizontalTextAlignment().name() : null)
                .verticalTextAlignment(entity.getVerticalTextAlignment() != null ? entity.getVerticalTextAlignment().name() : null)
                // Additional Properties
                .keyboard(entity.getKeyboard() != null ? entity.getKeyboard().name() : null)
                .isPassword(entity.getIsPassword())
                .maxLength(entity.getMaxLength())
                // Shadow
                .shadow(mapEntryShadowToDto(entity.getShadow()))
                // Visual States
                .visualStates(entity.getVisualStates() != null ?
                        entity.getVisualStates().stream()
                                .map(this::mapEntryVisualStateToDto)
                                .toList() : null)
                .build();
    }

    private dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.entry.EntryStyleDto.ShadowDto mapEntryShadowToDto(
            dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.entry.EntryShadowEntity shadow) {
        if (shadow == null) {
            return null;
        }
        return dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.entry.EntryStyleDto.ShadowDto.builder()
                .shadowBrush(shadow.getShadowBrush())
                .shadowOpacity(shadow.getShadowOpacity())
                .shadowRadius(shadow.getShadowRadius())
                .shadowOffset(shadow.getShadowOffset())
                .build();
    }

    private dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.entry.EntryStyleDto.VisualStateDto mapEntryVisualStateToDto(
            dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.entry.EntryVisualStateEntity state) {
        return dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.entry.EntryStyleDto.VisualStateDto.builder()
                .name(state.getStateName())
                .opacity(state.getOpacity())
                .backgroundColor(state.getBackgroundColor())
                .textColor(state.getTextColor())
                .shadow(mapEntryVisualStateShadowToDto(state.getShadow()))
                .build();
    }

    private dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.entry.EntryStyleDto.ShadowDto mapEntryVisualStateShadowToDto(
            dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.entry.EntryVisualStateShadowEntity shadow) {
        if (shadow == null) {
            return null;
        }
        return dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.entry.EntryStyleDto.ShadowDto.builder()
                .shadowBrush(shadow.getShadowBrush())
                .shadowOpacity(shadow.getShadowOpacity())
                .shadowRadius(shadow.getShadowRadius())
                .shadowOffset(shadow.getShadowOffset())
                .build();
    }

}
