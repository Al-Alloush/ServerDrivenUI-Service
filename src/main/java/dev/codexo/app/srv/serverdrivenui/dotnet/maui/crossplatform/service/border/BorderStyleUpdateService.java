package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.service.border;

import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.border.BorderStyleDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.border.BorderStyleUpdateDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.border.BorderShadowEntity;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.border.BorderStyleEntity;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.border.LayoutOptions;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.border.StrokeLineCap;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.border.StrokeLineJoin;
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
        // Border Properties
        if (dto.getBorderColor() != null) {
            entity.setStroke(dto.getBorderColor());
            log.debug("Updated borderColor to: {}", dto.getBorderColor());
        }
        if (dto.getBorderWidth() != null) {
            entity.setStrokeThickness(dto.getBorderWidth().doubleValue());
            log.debug("Updated borderWidth to: {}", dto.getBorderWidth());
        }
        if (dto.getCornerRadius() != null) {
            // Convert cornerRadius to strokeShape format
            entity.setStrokeShape("RoundRectangle " + dto.getCornerRadius());
            log.debug("Updated cornerRadius to: {}", dto.getCornerRadius());
        }
        if (dto.getBackgroundColor() != null) {
            entity.setBackground(dto.getBackgroundColor());
            log.debug("Updated backgroundColor to: {}", dto.getBackgroundColor());
        }
        if (dto.getStrokeShape() != null) {
            entity.setStrokeShape(dto.getStrokeShape());
            log.debug("Updated strokeShape to: {}", dto.getStrokeShape());
        }
        if (dto.getStrokeThickness() != null) {
            entity.setStrokeThickness(Double.parseDouble(dto.getStrokeThickness()));
            log.debug("Updated strokeThickness to: {}", dto.getStrokeThickness());
        }
        if (dto.getStrokeDashArray() != null) {
            entity.setStrokeDashArray(dto.getStrokeDashArray());
            log.debug("Updated strokeDashArray to: {}", dto.getStrokeDashArray());
        }
        if (dto.getStrokeDashOffset() != null) {
            entity.setStrokeDashOffset(Double.parseDouble(dto.getStrokeDashOffset()));
            log.debug("Updated strokeDashOffset to: {}", dto.getStrokeDashOffset());
        }
        if (dto.getStrokeLineCap() != null) {
            entity.setStrokeLineCap(StrokeLineCap.valueOf(dto.getStrokeLineCap()));
            log.debug("Updated strokeLineCap to: {}", dto.getStrokeLineCap());
        }
        if (dto.getStrokeLineJoin() != null) {
            entity.setStrokeLineJoin(StrokeLineJoin.valueOf(dto.getStrokeLineJoin()));
            log.debug("Updated strokeLineJoin to: {}", dto.getStrokeLineJoin());
        }

        // Layout
        if (dto.getPadding() != null) {
            entity.setPadding(dto.getPadding());
            log.debug("Updated padding to: {}", dto.getPadding());
        }
        if (dto.getMargin() != null) {
            entity.setMargin(dto.getMargin());
            log.debug("Updated margin to: {}", dto.getMargin());
        }
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
        if (dto.getHorizontalOptions() != null) {
            entity.setHorizontalOptions(LayoutOptions.valueOf(dto.getHorizontalOptions()));
            log.debug("Updated horizontalOptions to: {}", dto.getHorizontalOptions());
        }
        if (dto.getVerticalOptions() != null) {
            entity.setVerticalOptions(LayoutOptions.valueOf(dto.getVerticalOptions()));
            log.debug("Updated verticalOptions to: {}", dto.getVerticalOptions());
        }

        // Appearance
        if (dto.getOpacity() != null) {
            entity.setOpacity(dto.getOpacity());
            log.debug("Updated opacity to: {}", dto.getOpacity());
        }
        if (dto.getIsVisible() != null) {
            entity.setIsVisible(dto.getIsVisible());
            log.debug("Updated isVisible to: {}", dto.getIsVisible());
        }
        if (dto.getIsEnabled() != null) {
            entity.setIsEnabled(dto.getIsEnabled());
            log.debug("Updated isEnabled to: {}", dto.getIsEnabled());
        }

        // Accessibility
        if (dto.getSemanticDescription() != null) {
            entity.setAutomationId(dto.getSemanticDescription());
            log.debug("Updated semanticDescription to: {}", dto.getSemanticDescription());
        }
        if (dto.getSemanticHint() != null) {
            // Store semantic hint if entity supports it, otherwise log
            log.debug("Semantic hint provided but not stored in entity: {}", dto.getSemanticHint());
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
                // Border Properties
                .borderColor(entity.getStroke())
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
                .shadow(mapShadowToDto(entity.getShadow()))
                // Accessibility
                .semanticDescription(entity.getAutomationId())
                .semanticHint(null)
                // Visual states - not supported yet
                .visualStates(null)
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
}
