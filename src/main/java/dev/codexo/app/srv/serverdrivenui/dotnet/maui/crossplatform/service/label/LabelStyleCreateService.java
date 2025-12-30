package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.service.label;

import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.label.LabelStyleCreateDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.label.LabelStyleDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.label.*;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.repository.LabelStyleRepository;
import dev.codexo.app.srv.serverdrivenui.model.entity.PlatformThemeEntity;
import dev.codexo.app.srv.serverdrivenui.repository.PlatformThemeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class LabelStyleCreateService {

    private final LabelStyleRepository labelStyleRepository;
    private final PlatformThemeRepository platformThemeRepository;

    /**
     * Create a new label style with the provided DTO.
     *
     * @param createDto The DTO containing the fields for the new label style
     * @return The created LabelStyleDto
     * @throws EntityNotFoundException if theme not found
     * @throws IllegalArgumentException if a label with the same key already exists for the theme
     */
    @Transactional
    public LabelStyleDto createLabelStyle(LabelStyleCreateDto createDto) {
        log.debug("Creating new label style with key: {} for theme: {}", createDto.getKey(), createDto.getThemeId());

        // Find the theme
        PlatformThemeEntity theme = platformThemeRepository.findById(createDto.getThemeId())
                .orElseThrow(() -> new EntityNotFoundException("Theme not found with ID: " + createDto.getThemeId()));

        // Check if a label with the same key already exists for this theme
        if (labelStyleRepository.findByThemeIdAndKey(createDto.getThemeId(), createDto.getKey()).isPresent()) {
            throw new IllegalArgumentException("Label style with key '" + createDto.getKey() + "' already exists for this theme");
        }

        // Build the entity
        LabelStyleEntity entity = buildEntityFromDto(createDto, theme);

        // Save the entity
        LabelStyleEntity savedEntity = labelStyleRepository.save(entity);
        log.info("Successfully created label style with ID: {} and key: {}", savedEntity.getId(), savedEntity.getKey());

        return convertToDto(savedEntity);
    }

    /**
     * Build a LabelStyleEntity from the CreateDto.
     */
    private LabelStyleEntity buildEntityFromDto(LabelStyleCreateDto dto, PlatformThemeEntity theme) {
        LabelStyleEntity entity = LabelStyleEntity.builder()
                .theme(theme)
                .key(dto.getKey())
                .createdAt(OffsetDateTime.now())
                .visualStates(new ArrayList<>())
                .build();

        // Apply optional fields if provided
        applyOptionalFields(entity, dto);

        return entity;
    }

    /**
     * Apply optional fields from DTO to entity.
     */
    private void applyOptionalFields(LabelStyleEntity entity, LabelStyleCreateDto dto) {
        // Text Content
        if (dto.getText() != null) {
            entity.setText(dto.getText());
        }
        if (dto.getTextColor() != null) {
            entity.setTextColor(dto.getTextColor());
        }
        if (dto.getFormattedText() != null) {
            entity.setFormattedText(dto.getFormattedText());
        }

        // Font Properties
        if (dto.getFontFamily() != null) {
            entity.setFontFamily(dto.getFontFamily());
        }
        if (dto.getFontSize() != null) {
            entity.setFontSize(dto.getFontSize());
        }
        if (dto.getFontAttributes() != null) {
            entity.setFontAttributes(FontAttributes.valueOf(dto.getFontAttributes().toUpperCase()));
        }
        if (dto.getFontAutoScalingEnabled() != null) {
            entity.setFontAutoScalingEnabled(dto.getFontAutoScalingEnabled());
        }

        // Text Layout
        if (dto.getTextTransform() != null) {
            entity.setTextTransform(TextTransform.valueOf(dto.getTextTransform().toUpperCase()));
        }
        if (dto.getCharacterSpacing() != null) {
            entity.setCharacterSpacing(dto.getCharacterSpacing());
        }
        if (dto.getLineBreakMode() != null) {
            entity.setLineBreakMode(LineBreakMode.valueOf(dto.getLineBreakMode().toUpperCase()));
        }
        if (dto.getMaxLines() != null) {
            entity.setMaxLines(dto.getMaxLines());
        }
        if (dto.getLineHeight() != null) {
            entity.setLineHeight(dto.getLineHeight());
        }

        // Text Alignment
        if (dto.getHorizontalTextAlignment() != null) {
            entity.setHorizontalTextAlignment(TextAlignment.valueOf(dto.getHorizontalTextAlignment().toUpperCase()));
        }
        if (dto.getVerticalTextAlignment() != null) {
            entity.setVerticalTextAlignment(TextAlignment.valueOf(dto.getVerticalTextAlignment().toUpperCase()));
        }

        // Text Decoration
        if (dto.getTextDecorations() != null) {
            entity.setTextDecorations(TextDecorations.valueOf(dto.getTextDecorations().toUpperCase()));
        }

        // Padding
        if (dto.getPadding() != null) {
            entity.setPadding(dto.getPadding());
        }

        // Size
        if (dto.getHeightRequest() != null) {
            entity.setHeightRequest(dto.getHeightRequest());
        }
        if (dto.getWidthRequest() != null) {
            entity.setWidthRequest(dto.getWidthRequest());
        }
        if (dto.getMinimumHeightRequest() != null) {
            entity.setMinimumHeightRequest(dto.getMinimumHeightRequest());
        }
        if (dto.getMinimumWidthRequest() != null) {
            entity.setMinimumWidthRequest(dto.getMinimumWidthRequest());
        }
        if (dto.getMaximumHeightRequest() != null) {
            entity.setMaximumHeightRequest(dto.getMaximumHeightRequest());
        }
        if (dto.getMaximumWidthRequest() != null) {
            entity.setMaximumWidthRequest(dto.getMaximumWidthRequest());
        }

        // Layout
        if (dto.getHorizontalOptions() != null) {
            entity.setHorizontalOptions(LayoutOptions.valueOf(dto.getHorizontalOptions().toUpperCase()));
        }
        if (dto.getVerticalOptions() != null) {
            entity.setVerticalOptions(LayoutOptions.valueOf(dto.getVerticalOptions().toUpperCase()));
        }
        if (dto.getMargin() != null) {
            entity.setMargin(dto.getMargin());
        }

        // Background
        if (dto.getBackgroundColor() != null) {
            entity.setBackgroundColor(dto.getBackgroundColor());
        }

        // Visibility and Interaction
        if (dto.getIsVisible() != null) {
            entity.setIsVisible(dto.getIsVisible());
        }
        if (dto.getIsEnabled() != null) {
            entity.setIsEnabled(dto.getIsEnabled());
        }
        if (dto.getOpacity() != null) {
            entity.setOpacity(dto.getOpacity());
        }
        if (dto.getInputTransparent() != null) {
            entity.setInputTransparent(dto.getInputTransparent());
        }

        // Transforms
        if (dto.getAnchorX() != null) {
            entity.setAnchorX(dto.getAnchorX());
        }
        if (dto.getAnchorY() != null) {
            entity.setAnchorY(dto.getAnchorY());
        }
        if (dto.getRotation() != null) {
            entity.setRotation(dto.getRotation());
        }
        if (dto.getRotationX() != null) {
            entity.setRotationX(dto.getRotationX());
        }
        if (dto.getRotationY() != null) {
            entity.setRotationY(dto.getRotationY());
        }
        if (dto.getScale() != null) {
            entity.setScale(dto.getScale());
        }
        if (dto.getScaleX() != null) {
            entity.setScaleX(dto.getScaleX());
        }
        if (dto.getScaleY() != null) {
            entity.setScaleY(dto.getScaleY());
        }
        if (dto.getTranslationX() != null) {
            entity.setTranslationX(dto.getTranslationX());
        }
        if (dto.getTranslationY() != null) {
            entity.setTranslationY(dto.getTranslationY());
        }

        // Z-Index
        if (dto.getZIndex() != null) {
            entity.setZIndex(dto.getZIndex());
        }

        // Flow Direction
        if (dto.getFlowDirection() != null) {
            entity.setFlowDirection(FlowDirection.valueOf(dto.getFlowDirection().toUpperCase()));
        }

        // Semantics
        if (dto.getAutomationId() != null) {
            entity.setAutomationId(dto.getAutomationId());
        }

        // Shadow
        if (dto.getShadow() != null) {
            LabelStyleCreateDto.ShadowDto shadowDto = dto.getShadow();
            LabelShadowEntity shadow = LabelShadowEntity.builder()
                    .labelStyle(entity)
                    .shadowBrush(shadowDto.getShadowBrush())
                    .shadowOpacity(shadowDto.getShadowOpacity())
                    .shadowRadius(shadowDto.getShadowRadius())
                    .shadowOffset(shadowDto.getShadowOffset())
                    .createdAt(OffsetDateTime.now())
                    .build();
            entity.setShadow(shadow);
        }

        // Visual States
        if (dto.getVisualStates() != null && !dto.getVisualStates().isEmpty()) {
            for (LabelStyleCreateDto.VisualStateDto vsDto : dto.getVisualStates()) {
                LabelVisualStateEntity visualState = LabelVisualStateEntity.builder()
                        .labelStyle(entity)
                        .stateName(vsDto.getName())
                        .opacity(vsDto.getOpacity())
                        .textColor(vsDto.getTextColor())
                        .fontFamily(vsDto.getFontFamily())
                        .fontAttributes(vsDto.getFontAttributes() != null ?
                                FontAttributes.valueOf(vsDto.getFontAttributes().toUpperCase()) : null)
                        .createdAt(OffsetDateTime.now())
                        .build();

                // Add shadow to visual state if provided
                if (vsDto.getShadow() != null) {
                    LabelVisualStateShadowEntity vsShadow = LabelVisualStateShadowEntity.builder()
                            .labelVisualState(visualState)
                            .shadowBrush(vsDto.getShadow().getShadowBrush())
                            .shadowOpacity(vsDto.getShadow().getShadowOpacity())
                            .shadowRadius(vsDto.getShadow().getShadowRadius())
                            .shadowOffset(vsDto.getShadow().getShadowOffset())
                            .createdAt(OffsetDateTime.now())
                            .build();
                    visualState.setShadow(vsShadow);
                }

                entity.getVisualStates().add(visualState);
            }
        }
    }

    /**
     * Convert LabelStyleEntity to LabelStyleDto.
     */
    private LabelStyleDto convertToDto(LabelStyleEntity entity) {
        return LabelStyleDto.builder()
                .id(entity.getId())
                .key(entity.getKey())
                // Text Content
                .text(entity.getText())
                .textColor(entity.getTextColor())
                .formattedText(entity.getFormattedText())
                // Font Properties
                .fontFamily(entity.getFontFamily())
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
                .shadow(mapShadowToDto(entity.getShadow()))
                // Visual States
                .visualStates(entity.getVisualStates() != null ?
                        entity.getVisualStates().stream()
                                .map(this::mapVisualStateToDto)
                                .toList() : null)
                .build();
    }

    /**
     * Maps LabelShadowEntity to ShadowDto.
     */
    private LabelStyleDto.ShadowDto mapShadowToDto(LabelShadowEntity shadow) {
        if (shadow == null) {
            return null;
        }
        return LabelStyleDto.ShadowDto.builder()
                .shadowBrush(shadow.getShadowBrush())
                .shadowOpacity(shadow.getShadowOpacity())
                .shadowRadius(shadow.getShadowRadius())
                .shadowOffset(shadow.getShadowOffset())
                .build();
    }

    /**
     * Maps LabelVisualStateEntity to VisualStateDto.
     */
    private LabelStyleDto.VisualStateDto mapVisualStateToDto(LabelVisualStateEntity visualState) {
        if (visualState == null) {
            return null;
        }
        return LabelStyleDto.VisualStateDto.builder()
                .name(visualState.getStateName())
                .opacity(visualState.getOpacity())
                .textColor(visualState.getTextColor())
                .fontFamily(visualState.getFontFamily())
                .fontAttributes(visualState.getFontAttributes() != null ? visualState.getFontAttributes().name() : null)
                .shadow(mapVisualStateShadowToDto(visualState.getShadow()))
                .build();
    }

    /**
     * Maps LabelVisualStateShadowEntity to ShadowDto.
     */
    private LabelStyleDto.ShadowDto mapVisualStateShadowToDto(LabelVisualStateShadowEntity shadow) {
        if (shadow == null) {
            return null;
        }
        return LabelStyleDto.ShadowDto.builder()
                .shadowBrush(shadow.getShadowBrush())
                .shadowOpacity(shadow.getShadowOpacity())
                .shadowRadius(shadow.getShadowRadius())
                .shadowOffset(shadow.getShadowOffset())
                .build();
    }
}

