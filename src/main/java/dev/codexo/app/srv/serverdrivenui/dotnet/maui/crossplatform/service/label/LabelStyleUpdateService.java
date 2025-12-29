package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.service.label;

import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.label.LabelStyleDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.label.LabelStyleUpdateDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.label.*;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.repository.LabelStyleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LabelStyleUpdateService {

    private final LabelStyleRepository labelStyleRepository;

    /**
     * Update a label style by its ID with the provided update DTO.
     * Only non-null fields in the DTO will be updated.
     *
     * @param labelId The ID of the label style to update
     * @param updateDto The DTO containing the fields to update
     * @return The updated LabelStyleDto
     * @throws EntityNotFoundException if label style not found
     */
    @Transactional
    public LabelStyleDto updateLabelStyle(String labelId, LabelStyleUpdateDto updateDto) {
        log.debug("Updating label style with ID: {}", labelId);

        LabelStyleEntity labelStyle = labelStyleRepository.findById(labelId)
                .orElseThrow(() -> new EntityNotFoundException("Label style not found with ID: " + labelId));

        applyUpdates(labelStyle, updateDto);

        LabelStyleEntity updatedLabelStyle = labelStyleRepository.save(labelStyle);
        log.info("Successfully updated label style with ID: {}", labelId);

        return convertToDto(updatedLabelStyle);
    }

    /**
     * Apply updates from DTO to entity.
     * Only updates fields that are non-null in the DTO.
     */
    private void applyUpdates(LabelStyleEntity entity, LabelStyleUpdateDto dto) {
        // Text Content
        if (dto.getText() != null) {
            entity.setText(dto.getText());
            log.debug("Updated text to: {}", dto.getText());
        }
        if (dto.getTextColor() != null) {
            entity.setTextColor(dto.getTextColor());
            log.debug("Updated textColor to: {}", dto.getTextColor());
        }
        if (dto.getFormattedText() != null) {
            entity.setFormattedText(dto.getFormattedText());
            log.debug("Updated formattedText to: {}", dto.getFormattedText());
        }

        // Font Properties
        if (dto.getFontFamily() != null) {
            entity.setFontFamily(dto.getFontFamily());
            log.debug("Updated fontFamily to: {}", dto.getFontFamily());
        }
        if (dto.getFontSize() != null) {
            entity.setFontSize(dto.getFontSize());
            log.debug("Updated fontSize to: {}", dto.getFontSize());
        }
        if (dto.getFontAttributes() != null) {
            entity.setFontAttributes(FontAttributes.valueOf(dto.getFontAttributes().toUpperCase()));
            log.debug("Updated fontAttributes to: {}", dto.getFontAttributes());
        }
        if (dto.getFontAutoScalingEnabled() != null) {
            entity.setFontAutoScalingEnabled(dto.getFontAutoScalingEnabled());
            log.debug("Updated fontAutoScalingEnabled to: {}", dto.getFontAutoScalingEnabled());
        }

        // Text Layout
        if (dto.getTextTransform() != null) {
            entity.setTextTransform(TextTransform.valueOf(dto.getTextTransform().toUpperCase()));
            log.debug("Updated textTransform to: {}", dto.getTextTransform());
        }
        if (dto.getCharacterSpacing() != null) {
            entity.setCharacterSpacing(dto.getCharacterSpacing());
            log.debug("Updated characterSpacing to: {}", dto.getCharacterSpacing());
        }
        if (dto.getLineBreakMode() != null) {
            entity.setLineBreakMode(LineBreakMode.valueOf(dto.getLineBreakMode().toUpperCase()));
            log.debug("Updated lineBreakMode to: {}", dto.getLineBreakMode());
        }
        if (dto.getMaxLines() != null) {
            entity.setMaxLines(dto.getMaxLines());
            log.debug("Updated maxLines to: {}", dto.getMaxLines());
        }
        if (dto.getLineHeight() != null) {
            entity.setLineHeight(dto.getLineHeight());
            log.debug("Updated lineHeight to: {}", dto.getLineHeight());
        }

        // Text Alignment
        if (dto.getHorizontalTextAlignment() != null) {
            entity.setHorizontalTextAlignment(TextAlignment.valueOf(dto.getHorizontalTextAlignment().toUpperCase()));
            log.debug("Updated horizontalTextAlignment to: {}", dto.getHorizontalTextAlignment());
        }
        if (dto.getVerticalTextAlignment() != null) {
            entity.setVerticalTextAlignment(TextAlignment.valueOf(dto.getVerticalTextAlignment().toUpperCase()));
            log.debug("Updated verticalTextAlignment to: {}", dto.getVerticalTextAlignment());
        }

        // Text Decoration
        if (dto.getTextDecorations() != null) {
            entity.setTextDecorations(TextDecorations.valueOf(dto.getTextDecorations().toUpperCase()));
            log.debug("Updated textDecorations to: {}", dto.getTextDecorations());
        }

        // Padding
        if (dto.getPadding() != null) {
            entity.setPadding(dto.getPadding());
            log.debug("Updated padding to: {}", dto.getPadding());
        }

        // Size
        if (dto.getHeightRequest() != null) {
            entity.setHeightRequest(dto.getHeightRequest());
            log.debug("Updated heightRequest to: {}", dto.getHeightRequest());
        }
        if (dto.getWidthRequest() != null) {
            entity.setWidthRequest(dto.getWidthRequest());
            log.debug("Updated widthRequest to: {}", dto.getWidthRequest());
        }
        if (dto.getMinimumHeightRequest() != null) {
            entity.setMinimumHeightRequest(dto.getMinimumHeightRequest());
            log.debug("Updated minimumHeightRequest to: {}", dto.getMinimumHeightRequest());
        }
        if (dto.getMinimumWidthRequest() != null) {
            entity.setMinimumWidthRequest(dto.getMinimumWidthRequest());
            log.debug("Updated minimumWidthRequest to: {}", dto.getMinimumWidthRequest());
        }
        if (dto.getMaximumHeightRequest() != null) {
            entity.setMaximumHeightRequest(dto.getMaximumHeightRequest());
            log.debug("Updated maximumHeightRequest to: {}", dto.getMaximumHeightRequest());
        }
        if (dto.getMaximumWidthRequest() != null) {
            entity.setMaximumWidthRequest(dto.getMaximumWidthRequest());
            log.debug("Updated maximumWidthRequest to: {}", dto.getMaximumWidthRequest());
        }

        // Layout
        if (dto.getHorizontalOptions() != null) {
            entity.setHorizontalOptions(LayoutOptions.valueOf(dto.getHorizontalOptions().toUpperCase()));
            log.debug("Updated horizontalOptions to: {}", dto.getHorizontalOptions());
        }
        if (dto.getVerticalOptions() != null) {
            entity.setVerticalOptions(LayoutOptions.valueOf(dto.getVerticalOptions().toUpperCase()));
            log.debug("Updated verticalOptions to: {}", dto.getVerticalOptions());
        }
        if (dto.getMargin() != null) {
            entity.setMargin(dto.getMargin());
            log.debug("Updated margin to: {}", dto.getMargin());
        }

        // Background
        if (dto.getBackgroundColor() != null) {
            entity.setBackgroundColor(dto.getBackgroundColor());
            log.debug("Updated backgroundColor to: {}", dto.getBackgroundColor());
        }

        // Visibility and Interaction
        if (dto.getIsVisible() != null) {
            entity.setIsVisible(dto.getIsVisible());
            log.debug("Updated isVisible to: {}", dto.getIsVisible());
        }
        if (dto.getIsEnabled() != null) {
            entity.setIsEnabled(dto.getIsEnabled());
            log.debug("Updated isEnabled to: {}", dto.getIsEnabled());
        }
        if (dto.getOpacity() != null) {
            entity.setOpacity(dto.getOpacity());
            log.debug("Updated opacity to: {}", dto.getOpacity());
        }
        if (dto.getInputTransparent() != null) {
            entity.setInputTransparent(dto.getInputTransparent());
            log.debug("Updated inputTransparent to: {}", dto.getInputTransparent());
        }

        // Transforms
        if (dto.getAnchorX() != null) {
            entity.setAnchorX(dto.getAnchorX());
            log.debug("Updated anchorX to: {}", dto.getAnchorX());
        }
        if (dto.getAnchorY() != null) {
            entity.setAnchorY(dto.getAnchorY());
            log.debug("Updated anchorY to: {}", dto.getAnchorY());
        }
        if (dto.getRotation() != null) {
            entity.setRotation(dto.getRotation());
            log.debug("Updated rotation to: {}", dto.getRotation());
        }
        if (dto.getRotationX() != null) {
            entity.setRotationX(dto.getRotationX());
            log.debug("Updated rotationX to: {}", dto.getRotationX());
        }
        if (dto.getRotationY() != null) {
            entity.setRotationY(dto.getRotationY());
            log.debug("Updated rotationY to: {}", dto.getRotationY());
        }
        if (dto.getScale() != null) {
            entity.setScale(dto.getScale());
            log.debug("Updated scale to: {}", dto.getScale());
        }
        if (dto.getScaleX() != null) {
            entity.setScaleX(dto.getScaleX());
            log.debug("Updated scaleX to: {}", dto.getScaleX());
        }
        if (dto.getScaleY() != null) {
            entity.setScaleY(dto.getScaleY());
            log.debug("Updated scaleY to: {}", dto.getScaleY());
        }
        if (dto.getTranslationX() != null) {
            entity.setTranslationX(dto.getTranslationX());
            log.debug("Updated translationX to: {}", dto.getTranslationX());
        }
        if (dto.getTranslationY() != null) {
            entity.setTranslationY(dto.getTranslationY());
            log.debug("Updated translationY to: {}", dto.getTranslationY());
        }

        // Z-Index
        if (dto.getZIndex() != null) {
            entity.setZIndex(dto.getZIndex());
            log.debug("Updated zIndex to: {}", dto.getZIndex());
        }

        // Flow Direction
        if (dto.getFlowDirection() != null) {
            entity.setFlowDirection(FlowDirection.valueOf(dto.getFlowDirection().toUpperCase()));
            log.debug("Updated flowDirection to: {}", dto.getFlowDirection());
        }

        // Semantics
        if (dto.getAutomationId() != null) {
            entity.setAutomationId(dto.getAutomationId());
            log.debug("Updated automationId to: {}", dto.getAutomationId());
        }

        // Shadow
        if (dto.getShadow() != null) {
            LabelStyleUpdateDto.ShadowDto shadowDto = dto.getShadow();
            if (entity.getShadow() == null) {
                // Create new shadow entity
                LabelShadowEntity shadow = LabelShadowEntity.builder()
                        .labelStyle(entity)
                        .shadowBrush(shadowDto.getShadowBrush())
                        .shadowOpacity(shadowDto.getShadowOpacity())
                        .shadowRadius(shadowDto.getShadowRadius())
                        .shadowOffset(shadowDto.getShadowOffset())
                        .createdAt(java.time.OffsetDateTime.now())
                        .build();
                entity.setShadow(shadow);
                log.debug("Created new shadow for label style");
            } else {
                // Update existing shadow
                if (shadowDto.getShadowBrush() != null) {
                    entity.getShadow().setShadowBrush(shadowDto.getShadowBrush());
                }
                if (shadowDto.getShadowOpacity() != null) {
                    entity.getShadow().setShadowOpacity(shadowDto.getShadowOpacity());
                }
                if (shadowDto.getShadowRadius() != null) {
                    entity.getShadow().setShadowRadius(shadowDto.getShadowRadius());
                }
                if (shadowDto.getShadowOffset() != null) {
                    entity.getShadow().setShadowOffset(shadowDto.getShadowOffset());
                }
                log.debug("Updated shadow for label style");
            }
        }

        // Visual States
        if (dto.getVisualStates() != null && !dto.getVisualStates().isEmpty()) {
            // Clear existing visual states and add new ones
            entity.getVisualStates().clear();

            for (LabelStyleUpdateDto.VisualStateDto vsDto : dto.getVisualStates()) {
                LabelVisualStateEntity visualState = LabelVisualStateEntity.builder()
                        .labelStyle(entity)
                        .stateName(vsDto.getName())
                        .opacity(vsDto.getOpacity())
                        .textColor(vsDto.getTextColor())
                        .fontFamily(vsDto.getFontFamily())
                        .fontAttributes(vsDto.getFontAttributes() != null ?
                                FontAttributes.valueOf(vsDto.getFontAttributes().toUpperCase()) : null)
                        .createdAt(java.time.OffsetDateTime.now())
                        .build();

                // Add shadow to visual state if provided
                if (vsDto.getShadow() != null) {
                    LabelVisualStateShadowEntity vsShadow = LabelVisualStateShadowEntity.builder()
                            .labelVisualState(visualState)
                            .shadowBrush(vsDto.getShadow().getShadowBrush())
                            .shadowOpacity(vsDto.getShadow().getShadowOpacity())
                            .shadowRadius(vsDto.getShadow().getShadowRadius())
                            .shadowOffset(vsDto.getShadow().getShadowOffset())
                            .createdAt(java.time.OffsetDateTime.now())
                            .build();
                    visualState.setShadow(vsShadow);
                }

                entity.getVisualStates().add(visualState);
                log.debug("Added/Updated visual state: {} with shadow: {}", vsDto.getName(), vsDto.getShadow() != null);
            }
        }
    }

    /**
     * Convert LabelStyleEntity to LabelStyleDto.
     */
    private LabelStyleDto convertToDto(LabelStyleEntity entity) {
        return LabelStyleDto.builder()
                .id(entity.getId())
                .key(entity.getStyleKey())
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
