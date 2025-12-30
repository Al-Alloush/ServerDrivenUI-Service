package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.service.buttons;

import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.button.ButtonStyleCreateDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.button.ButtonStyleDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.button.*;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.repository.ButtonStyleRepository;
import dev.codexo.app.srv.serverdrivenui.model.entity.BrandIdentityEntity;
import dev.codexo.app.srv.serverdrivenui.model.entity.PlatformThemeEntity;
import dev.codexo.app.srv.serverdrivenui.repository.PlatformThemeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ButtonStyleCreateService {

    private final ButtonStyleRepository buttonStyleRepository;
    private final PlatformThemeRepository platformThemeRepository;

    /**
     * Create a new button style with the provided DTO.
     *
     * @param createDto The DTO containing the fields for the new button style
     * @return The created ButtonStyleDto
     * @throws EntityNotFoundException if theme not found
     * @throws IllegalArgumentException if a button with the same key already exists for the theme
     */
    @Transactional
    public ButtonStyleDto createButtonStyle(ButtonStyleCreateDto createDto) {
        log.debug("Creating new button style with key: {} for theme: {}", createDto.getKey(), createDto.getThemeId());

        // Find the theme
        PlatformThemeEntity theme = platformThemeRepository.findById(createDto.getThemeId())
                .orElseThrow(() -> new EntityNotFoundException("Theme not found with ID: " + createDto.getThemeId()));

        // Check if a button with the same key already exists for this theme
        List<ButtonStyleEntity> existingButtons = buttonStyleRepository.findByTheme(theme);
        boolean keyExists = existingButtons.stream()
                .anyMatch(b -> b.getStyleKey().equals(createDto.getKey()));
        if (keyExists) {
            throw new IllegalArgumentException("Button style with key '" + createDto.getKey() + "' already exists for this theme");
        }

        // Build the entity
        ButtonStyleEntity entity = buildEntityFromDto(createDto, theme);

        // Save the entity
        ButtonStyleEntity savedEntity = buttonStyleRepository.save(entity);
        log.info("Successfully created button style with ID: {} and key: {}", savedEntity.getId(), savedEntity.getStyleKey());

        BrandIdentityEntity brand = theme.getProjectPlatform().getProject().getBrandIdentity();
        return mapToDto(savedEntity, brand);
    }

    /**
     * Build a ButtonStyleEntity from the CreateDto.
     */
    private ButtonStyleEntity buildEntityFromDto(ButtonStyleCreateDto dto, PlatformThemeEntity theme) {
        ButtonStyleEntity entity = ButtonStyleEntity.builder()
                .theme(theme)
                .styleKey(dto.getKey())
                .createdAt(OffsetDateTime.now())
                .visualStates(new ArrayList<>())
                .build();

        // Build Appearance
        entity.setAppearance(ButtonStyleEntity.Appearance.builder()
                .text(dto.getText())
                .textColor(dto.getTextColor())
                .backgroundColor(dto.getBackgroundColor())
                .opacity(dto.getOpacity() != null ? dto.getOpacity() : 1.0)
                .isVisible(dto.getIsVisible() != null ? dto.getIsVisible() : true)
                .isEnabled(dto.getIsEnabled() != null ? dto.getIsEnabled() : true)
                .build());

        // Build Typography
        entity.setTypography(ButtonStyleEntity.Typography.builder()
                .fontFamily(dto.getFontFamily())
                .fontSize(dto.getFontSize())
                .FontAttributes(dto.getFontAttributes())
                .characterSpacing(dto.getCharacterSpacing())
                .lineBreakMode(dto.getLineBreakMode())
                .textTransform(dto.getTextTransform())
                .build());

        // Build Layout
        entity.setLayout(ButtonStyleEntity.Layout.builder()
                .padding(dto.getPadding())
                .margin(dto.getMargin())
                .heightRequest(dto.getHeightRequest())
                .widthRequest(dto.getWidthRequest())
                .minimumHeightRequest(dto.getMinimumHeightRequest())
                .minimumWidthRequest(dto.getMinimumWidthRequest())
                .HorizontalOptions(dto.getHorizontalOptions())
                .verticalOptions(dto.getVerticalOptions())
                .contentLayout(dto.getContentLayout())
                .build());

        // Build Border
        entity.setBorder(ButtonStyleEntity.Border.builder()
                .BorderColor(dto.getBorderColor())
                .borderWidth(dto.getBorderWidth())
                .cornerRadius(dto.getCornerRadius())
                .build());

        // Build Accessibility
        entity.setAccessibility(ButtonStyleEntity.Accessibility.builder()
                .semanticDescription(dto.getSemanticDescription())
                .semanticHint(dto.getSemanticHint())
                .build());

        // Set Image Source
        entity.setImageSource(dto.getImageSource());

        // Build Shadow
        if (dto.getShadow() != null) {
            ButtonShadowEntity shadow = ButtonShadowEntity.builder()
                    .buttonStyle(entity)
                    .shadowBrush(dto.getShadow().getShadowBrush())
                    .shadowOpacity(dto.getShadow().getShadowOpacity())
                    .shadowRadius(dto.getShadow().getShadowRadius())
                    .shadowOffsetX(dto.getShadow().getShadowOffset())
                    .createdAt(OffsetDateTime.now())
                    .build();
            entity.setShadow(shadow);
        }

        // Build Visual States
        if (dto.getVisualStates() != null && !dto.getVisualStates().isEmpty()) {
            for (ButtonStyleCreateDto.VisualStateDto vsDto : dto.getVisualStates()) {
                ButtonVisualStateEntity visualState = ButtonVisualStateEntity.builder()
                        .buttonStyle(entity)
                        .name(vsDto.getName())
                        .opacity(vsDto.getOpacity())
                        .textColor(vsDto.getTextColor())
                        .backgroundColor(vsDto.getBackgroundColor())
                        .borderColor(vsDto.getBorderColor())
                        .build();

                // Add shadow to visual state if provided
                if (vsDto.getShadow() != null) {
                    ButtonVisualStateShadowEntity vsShadow = ButtonVisualStateShadowEntity.builder()
                            .visualSateGroup(visualState)
                            .shadowBrush(vsDto.getShadow().getShadowBrush())
                            .shadowOpacity(vsDto.getShadow().getShadowOpacity())
                            .shadowRadius(vsDto.getShadow().getShadowRadius())
                            .shadowOffsetX(vsDto.getShadow().getShadowOffset())
                            .createdAt(OffsetDateTime.now())
                            .build();
                    visualState.setShadow(vsShadow);
                }

                entity.getVisualStates().add(visualState);
            }
        }

        return entity;
    }

    /**
     * Convert ButtonStyleEntity to ButtonStyleDto.
     */
    private ButtonStyleDto mapToDto(ButtonStyleEntity entity, BrandIdentityEntity brand) {
        return ButtonStyleDto.builder()
                .id(entity.getId())
                .key(entity.getStyleKey())
                .text(entity.getAppearance() != null ? entity.getAppearance().getText() : null)
                .textColor(entity.getAppearance() != null ? entity.getAppearance().getTextColor() : null)
                .backgroundColor(entity.getAppearance() != null ? entity.getAppearance().getBackgroundColor() : null)
                .opacity(entity.getAppearance() != null ? entity.getAppearance().getOpacity() : null)
                .isVisible(entity.getAppearance() != null ? entity.getAppearance().isVisible() : null)
                .isEnabled(entity.getAppearance() != null ? entity.getAppearance().isEnabled() : null)
                .fontFamily(entity.getTypography() != null ? entity.getTypography().getFontFamily() : (brand != null ? brand.getFontFamily() : null))
                .fontSize(entity.getTypography() != null ? entity.getTypography().getFontSize() : null)
                .fontAttributes(entity.getTypography() != null ? entity.getTypography().getFontAttributes() : null)
                .characterSpacing(entity.getTypography() != null ? entity.getTypography().getCharacterSpacing() : null)
                .lineBreakMode(entity.getTypography() != null ? entity.getTypography().getLineBreakMode() : null)
                .textTransform(entity.getTypography() != null ? entity.getTypography().getTextTransform() : null)
                .padding(entity.getLayout() != null ? entity.getLayout().getPadding() : null)
                .margin(entity.getLayout() != null ? entity.getLayout().getMargin() : null)
                .heightRequest(entity.getLayout() != null ? entity.getLayout().getHeightRequest() : null)
                .widthRequest(entity.getLayout() != null ? entity.getLayout().getWidthRequest() : null)
                .minimumHeightRequest(entity.getLayout() != null ? entity.getLayout().getMinimumHeightRequest() : null)
                .minimumWidthRequest(entity.getLayout() != null ? entity.getLayout().getMinimumWidthRequest() : null)
                .horizontalOptions(entity.getLayout() != null ? entity.getLayout().getHorizontalOptions() : null)
                .verticalOptions(entity.getLayout() != null ? entity.getLayout().getVerticalOptions() : null)
                .contentLayout(entity.getLayout() != null ? entity.getLayout().getContentLayout() : null)
                .borderColor(entity.getBorder() != null ? entity.getBorder().getBorderColor() : null)
                .borderWidth(entity.getBorder() != null ? entity.getBorder().getBorderWidth() : null)
                .cornerRadius(entity.getBorder() != null ? entity.getBorder().getCornerRadius() : null)
                .imageSource(entity.getImageSource())
                .shadow(mapShadowToDto(entity.getShadow()))
                .semanticDescription(entity.getAccessibility() != null ? entity.getAccessibility().getSemanticDescription() : null)
                .semanticHint(entity.getAccessibility() != null ? entity.getAccessibility().getSemanticHint() : null)
                .visualStates(entity.getVisualStates() != null ?
                        entity.getVisualStates().stream()
                                .map(this::mapVisualStateToDto)
                                .toList() : List.of())
                .build();
    }

    private ButtonStyleDto.ShadowDto mapShadowToDto(ButtonShadowEntity shadow) {
        if (shadow == null) return null;
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
        if (shadow == null) return null;
        return ButtonStyleDto.ShadowDto.builder()
                .shadowBrush(shadow.getShadowBrush())
                .shadowOpacity(shadow.getShadowOpacity())
                .shadowRadius(shadow.getShadowRadius())
                .shadowOffset(shadow.getShadowOffsetX())
                .build();
    }
}

