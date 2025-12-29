package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.service.entry;

import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.entry.EntryStyleDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.entry.EntryStyleUpdateDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.entry.*;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.repository.EntryStyleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EntryStyleUpdateService {

    private final EntryStyleRepository entryStyleRepository;

    @Transactional
    public EntryStyleDto updateEntryStyle(String entryId, EntryStyleUpdateDto updateDto) {
        log.debug("Updating entry style with ID: {}", entryId);

        EntryStyleEntity entryStyle = entryStyleRepository.findById(entryId)
                .orElseThrow(() -> new EntityNotFoundException("Entry style not found with ID: " + entryId));

        applyUpdates(entryStyle, updateDto);

        EntryStyleEntity updatedEntryStyle = entryStyleRepository.save(entryStyle);
        log.info("Successfully updated entry style with ID: {}", entryId);

        return convertToDto(updatedEntryStyle);
    }

    private void applyUpdates(EntryStyleEntity entity, EntryStyleUpdateDto dto) {
        // Colors
        if (dto.getBackgroundColor() != null) {
            entity.setBackgroundColor(dto.getBackgroundColor());
            log.debug("Updated backgroundColor to: {}", dto.getBackgroundColor());
        }
        if (dto.getTextColor() != null) {
            entity.setTextColor(dto.getTextColor());
            log.debug("Updated textColor to: {}", dto.getTextColor());
        }
        if (dto.getPlaceholderColor() != null) {
            entity.setPlaceholderColor(dto.getPlaceholderColor());
            log.debug("Updated placeholderColor to: {}", dto.getPlaceholderColor());
        }
        if (dto.getCursorColor() != null) {
            entity.setCursorColor(dto.getCursorColor());
            log.debug("Updated cursorColor to: {}", dto.getCursorColor());
        }
        if (dto.getSelectionHighlightColor() != null) {
            entity.setSelectionHighlightColor(dto.getSelectionHighlightColor());
            log.debug("Updated selectionHighlightColor to: {}", dto.getSelectionHighlightColor());
        }

        // Font Properties
        if (dto.getFontSize() != null) {
            entity.setFontSize(dto.getFontSize());
            log.debug("Updated fontSize to: {}", dto.getFontSize());
        }
        if (dto.getFontFamily() != null) {
            entity.setFontFamily(dto.getFontFamily());
            log.debug("Updated fontFamily to: {}", dto.getFontFamily());
        }

        // Size and Layout
        if (dto.getHeightRequest() != null) {
            entity.setHeightRequest(dto.getHeightRequest());
            log.debug("Updated heightRequest to: {}", dto.getHeightRequest());
        }
        if (dto.getMargin() != null) {
            entity.setMargin(dto.getMargin());
            log.debug("Updated margin to: {}", dto.getMargin());
        }

        // Entry Behavior
        if (dto.getClearButtonVisibility() != null) {
            entity.setClearButtonVisibility(EntryStyleEntity.ClearButtonVisibility.valueOf(dto.getClearButtonVisibility().toUpperCase()));
            log.debug("Updated clearButtonVisibility to: {}", dto.getClearButtonVisibility());
        }
        if (dto.getReturnType() != null) {
            entity.setReturnType(EntryStyleEntity.ReturnType.valueOf(dto.getReturnType().toUpperCase()));
            log.debug("Updated returnType to: {}", dto.getReturnType());
        }

        // Text Alignment
        if (dto.getHorizontalTextAlignment() != null) {
            entity.setHorizontalTextAlignment(EntryStyleEntity.TextAlignment.valueOf(dto.getHorizontalTextAlignment().toUpperCase()));
            log.debug("Updated horizontalTextAlignment to: {}", dto.getHorizontalTextAlignment());
        }
        if (dto.getVerticalTextAlignment() != null) {
            entity.setVerticalTextAlignment(EntryStyleEntity.TextAlignment.valueOf(dto.getVerticalTextAlignment().toUpperCase()));
            log.debug("Updated verticalTextAlignment to: {}", dto.getVerticalTextAlignment());
        }

        // Additional Properties
        if (dto.getKeyboard() != null) {
            entity.setKeyboard(EntryStyleEntity.Keyboard.valueOf(dto.getKeyboard().toUpperCase()));
            log.debug("Updated keyboard to: {}", dto.getKeyboard());
        }
        if (dto.getIsPassword() != null) {
            entity.setIsPassword(dto.getIsPassword());
            log.debug("Updated isPassword to: {}", dto.getIsPassword());
        }
        if (dto.getMaxLength() != null) {
            entity.setMaxLength(dto.getMaxLength());
            log.debug("Updated maxLength to: {}", dto.getMaxLength());
        }

        // Shadow
        if (dto.getShadow() != null) {
            EntryStyleUpdateDto.ShadowDto shadowDto = dto.getShadow();
            if (entity.getShadow() == null) {
                EntryShadowEntity shadow = EntryShadowEntity.builder()
                        .entryStyle(entity)
                        .shadowBrush(shadowDto.getShadowBrush())
                        .shadowOpacity(shadowDto.getShadowOpacity())
                        .shadowRadius(shadowDto.getShadowRadius())
                        .shadowOffset(shadowDto.getShadowOffset())
                        .createdAt(java.time.OffsetDateTime.now())
                        .build();
                entity.setShadow(shadow);
                log.debug("Created new shadow for entry style");
            } else {
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
                log.debug("Updated shadow for entry style");
            }
        }

        // Visual States
        if (dto.getVisualStates() != null && !dto.getVisualStates().isEmpty()) {
            entity.getVisualStates().clear();

            for (EntryStyleUpdateDto.VisualStateDto vsDto : dto.getVisualStates()) {
                EntryVisualStateEntity visualState = EntryVisualStateEntity.builder()
                        .entryStyle(entity)
                        .stateName(vsDto.getName())
                        .opacity(vsDto.getOpacity())
                        .backgroundColor(vsDto.getBackgroundColor())
                        .textColor(vsDto.getTextColor())
                        .createdAt(java.time.OffsetDateTime.now())
                        .build();

                if (vsDto.getShadow() != null) {
                    EntryVisualStateShadowEntity vsShadow = EntryVisualStateShadowEntity.builder()
                            .entryVisualState(visualState)
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

    private EntryStyleDto convertToDto(EntryStyleEntity entity) {
        return EntryStyleDto.builder()
                .id(entity.getId())
                .key(entity.getStyleKey())
                .backgroundColor(entity.getBackgroundColor())
                .textColor(entity.getTextColor())
                .placeholderColor(entity.getPlaceholderColor())
                .cursorColor(entity.getCursorColor())
                .selectionHighlightColor(entity.getSelectionHighlightColor())
                .fontSize(entity.getFontSize())
                .fontFamily(entity.getFontFamily())
                .heightRequest(entity.getHeightRequest())
                .margin(entity.getMargin())
                .clearButtonVisibility(entity.getClearButtonVisibility() != null ? entity.getClearButtonVisibility().name() : null)
                .returnType(entity.getReturnType() != null ? entity.getReturnType().name() : null)
                .horizontalTextAlignment(entity.getHorizontalTextAlignment() != null ? entity.getHorizontalTextAlignment().name() : null)
                .verticalTextAlignment(entity.getVerticalTextAlignment() != null ? entity.getVerticalTextAlignment().name() : null)
                .keyboard(entity.getKeyboard() != null ? entity.getKeyboard().name() : null)
                .isPassword(entity.getIsPassword())
                .maxLength(entity.getMaxLength())
                .shadow(mapShadowToDto(entity.getShadow()))
                .visualStates(entity.getVisualStates() != null ?
                        entity.getVisualStates().stream()
                                .map(this::mapVisualStateToDto)
                                .toList() : null)
                .build();
    }

    private EntryStyleDto.ShadowDto mapShadowToDto(EntryShadowEntity shadow) {
        if (shadow == null) {
            return null;
        }
        return EntryStyleDto.ShadowDto.builder()
                .shadowBrush(shadow.getShadowBrush())
                .shadowOpacity(shadow.getShadowOpacity())
                .shadowRadius(shadow.getShadowRadius())
                .shadowOffset(shadow.getShadowOffset())
                .build();
    }

    private EntryStyleDto.VisualStateDto mapVisualStateToDto(EntryVisualStateEntity visualState) {
        if (visualState == null) {
            return null;
        }
        return EntryStyleDto.VisualStateDto.builder()
                .name(visualState.getStateName())
                .opacity(visualState.getOpacity())
                .backgroundColor(visualState.getBackgroundColor())
                .textColor(visualState.getTextColor())
                .shadow(mapVisualStateShadowToDto(visualState.getShadow()))
                .build();
    }

    private EntryStyleDto.ShadowDto mapVisualStateShadowToDto(EntryVisualStateShadowEntity shadow) {
        if (shadow == null) {
            return null;
        }
        return EntryStyleDto.ShadowDto.builder()
                .shadowBrush(shadow.getShadowBrush())
                .shadowOpacity(shadow.getShadowOpacity())
                .shadowRadius(shadow.getShadowRadius())
                .shadowOffset(shadow.getShadowOffset())
                .build();
    }
}
