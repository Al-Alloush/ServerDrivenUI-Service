package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.service.border;

import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.border.BorderStyleDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.border.BorderStyleUpdateDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.border.*;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.repository.BorderStyleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class BorderStyleUpdateService {

    private final BorderStyleRepository borderStyleRepository;

    /**
     * Update a border style by its ID with the provided update DTO.
     * Only non-null fields in the DTO will be updated.
     *
     * @param borderId The ID of the border style to update
     * @param updateDto     The DTO containing the fields to update
     * @return The updated BorderStyleDto
     * @throws EntityNotFoundException if border style not found
     */
    @Transactional
    public BorderStyleDto updateBorderStyle(String borderId, BorderStyleUpdateDto updateDto) {
        log.debug("Updating border style with ID: {}", borderId);

        BorderStyleEntity borderStyle = borderStyleRepository.findById(borderId)
                .orElseThrow(() -> new EntityNotFoundException("Border style not found with ID: " + borderId));

        applyUpdates(borderStyle, updateDto);

        BorderStyleEntity updatedBorderStyle = borderStyleRepository.save(borderStyle);
        log.info("Successfully updated border style with ID: {}", borderId);

        return convertToDto(updatedBorderStyle);
    }

    /**
     * Apply updates from DTO to entity.
     * Only updates fields that are non-null in the DTO.
     */
    private void applyUpdates(BorderStyleEntity entity, BorderStyleUpdateDto dto) {
        // Background and Stroke
        if (dto.getBackground() != null) {
            entity.setBackground(dto.getBackground());
            log.debug("Updated background to: {}", dto.getBackground());
        }
        if (dto.getStroke() != null) {
            entity.setStroke(dto.getStroke());
            log.debug("Updated stroke to: {}", dto.getStroke());
        }
        if (dto.getStrokeThickness() != null) {
            entity.setStrokeThickness(dto.getStrokeThickness());
            log.debug("Updated strokeThickness to: {}", dto.getStrokeThickness());
        }

        // Stroke Dash Pattern
        if (dto.getStrokeDashArray() != null) {
            entity.setStrokeDashArray(dto.getStrokeDashArray());
            log.debug("Updated strokeDashArray to: {}", dto.getStrokeDashArray());
        }
        if (dto.getStrokeDashOffset() != null) {
            entity.setStrokeDashOffset(dto.getStrokeDashOffset());
            log.debug("Updated strokeDashOffset to: {}", dto.getStrokeDashOffset());
        }

        // Stroke Line Caps and Joins
        if (dto.getStrokeLineCap() != null) {
            entity.setStrokeLineCap(StrokeLineCap.valueOf(dto.getStrokeLineCap().toUpperCase()));
            log.debug("Updated strokeLineCap to: {}", dto.getStrokeLineCap());
        }
        if (dto.getStrokeLineJoin() != null) {
            entity.setStrokeLineJoin(StrokeLineJoin.valueOf(dto.getStrokeLineJoin().toUpperCase()));
            log.debug("Updated strokeLineJoin to: {}", dto.getStrokeLineJoin());
        }
        if (dto.getStrokeMiterLimit() != null) {
            entity.setStrokeMiterLimit(dto.getStrokeMiterLimit());
            log.debug("Updated strokeMiterLimit to: {}", dto.getStrokeMiterLimit());
        }

        // Corner Radius (StrokeShape)
        if (dto.getStrokeShape() != null) {
            entity.setStrokeShape(dto.getStrokeShape());
            log.debug("Updated strokeShape to: {}", dto.getStrokeShape());
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
            entity.setHorizontalOptions(LayoutOptions.valueOf(dto.getHorizontalOptions().toUpperCase().replace("AND", "AND_")));
            log.debug("Updated horizontalOptions to: {}", dto.getHorizontalOptions());
        }
        if (dto.getVerticalOptions() != null) {
            entity.setVerticalOptions(LayoutOptions.valueOf(dto.getVerticalOptions().toUpperCase().replace("AND", "AND_")));
            log.debug("Updated verticalOptions to: {}", dto.getVerticalOptions());
        }
        if (dto.getMargin() != null) {
            entity.setMargin(dto.getMargin());
            log.debug("Updated margin to: {}", dto.getMargin());
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
            BorderStyleUpdateDto.ShadowDto shadowDto = dto.getShadow();
            if (entity.getShadow() == null) {
                // Create new shadow entity
                BorderShadowEntity shadow = BorderShadowEntity.builder()
                        .borderStyle(entity)
                        .shadowBrush(shadowDto.getShadowBrush())
                        .shadowOpacity(shadowDto.getShadowOpacity())
                        .shadowRadius(shadowDto.getShadowRadius())
                        .shadowOffset(shadowDto.getShadowOffset())
                        .createdAt(OffsetDateTime.now())
                        .build();
                entity.setShadow(shadow);
                log.debug("Created new shadow for border style");
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
                log.debug("Updated shadow for border style");
            }
        }
    }

    /**
     * Convert BorderStyleEntity to BorderStyleDto.
     */
    private BorderStyleDto convertToDto(BorderStyleEntity entity) {
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
                .shadow(mapShadowToDto(entity.getShadow()))
                // Visual states - Now mapped
                .visualStates(entity.getVisualStates() != null ?
                        entity.getVisualStates().stream()
                                .map(this::mapVisualStateToDto)
                                .toList() : null)
                .build();
    }

    /**
     * Maps BorderShadowEntity to ShadowDto.
     */
    private BorderStyleDto.ShadowDto mapShadowToDto(BorderShadowEntity shadow) {
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
     * Maps BorderVisualStateEntity to VisualStateDto.
     */
    private BorderStyleDto.VisualStateDto mapVisualStateToDto(BorderVisualStateEntity visualState) {
        if (visualState == null) {
            return null;
        }
        return BorderStyleDto.VisualStateDto.builder()
                .name(visualState.getName())
                .opacity(visualState.getOpacity())
                .build();
    }
}
