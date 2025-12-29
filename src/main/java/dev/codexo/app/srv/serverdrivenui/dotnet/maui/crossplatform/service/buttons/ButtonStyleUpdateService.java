package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.service.buttons;

import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.button.ButtonStyleDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.button.ButtonStyleUpdateDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.button.*;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.repository.ButtonStyleRepository;
import dev.codexo.app.srv.serverdrivenui.model.entity.BrandIdentityEntity;
import dev.codexo.app.srv.serverdrivenui.model.entity.ProjectPlatformEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ButtonStyleUpdateService {

    private final ButtonStyleRepository buttonStyleRepository;

    public ButtonStyleUpdateService(ButtonStyleRepository buttonStyleRepository) {
        this.buttonStyleRepository = buttonStyleRepository;
    }

    @Transactional
    public ButtonStyleDto updateButtonStyle(
            String buttonId,
            ButtonStyleUpdateDto updateDto,
            ProjectPlatformEntity requestorProjectPlatform)
    {
        ButtonStyleEntity entity = buttonStyleRepository
                .findById(buttonId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Button style not found with id: " + buttonId
                ));

        // Security check: ensure the button belongs to the requestor's project-platform
        if (!entity.getTheme().getProjectPlatform().getId()
                .equals(requestorProjectPlatform.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You don't have permission to modify this button style"
            );
        }

        // Update style key
        entity.setStyleKey(updateDto.getKey());

        // Update Appearance
        updateAppearance(entity, updateDto);

        // Update Typography
        updateTypography(entity, updateDto);

        // Update Layout
        updateLayout(entity, updateDto);

        // Update Border
        updateBorder(entity, updateDto);

        // Update Image
        entity.setImageSource(updateDto.getImageSource());

        // Update Accessibility
        updateAccessibility(entity, updateDto);

        // Update Shadow (replace or remove)
        updateShadow(entity, updateDto);

        // Update Visual States (replace all) - FIX HERE
        updateVisualStates(entity, updateDto.getVisualStates());

        ButtonStyleEntity saved = buttonStyleRepository.save(entity);

        return mapToDto(saved, saved.getTheme().getProjectPlatform().getProject().getBrandIdentity());
    }

    private void updateAppearance(ButtonStyleEntity entity, ButtonStyleUpdateDto dto) {
        ButtonStyleEntity.Appearance appearance = entity.getAppearance();
        if (appearance == null) {
            appearance = new ButtonStyleEntity.Appearance();
            entity.setAppearance(appearance);
        }
        appearance.setText(dto.getText());
        appearance.setTextColor(dto.getTextColor());
        appearance.setBackgroundColor(dto.getBackgroundColor());
        appearance.setOpacity(dto.getOpacity());
        appearance.setVisible(dto.getIsVisible() != null && dto.getIsVisible());
        appearance.setEnabled(dto.getIsEnabled() != null && dto.getIsEnabled());
    }

    private void updateTypography(ButtonStyleEntity entity, ButtonStyleUpdateDto dto) {
        ButtonStyleEntity.Typography typography = entity.getTypography();
        if (typography == null) {
            typography = new ButtonStyleEntity.Typography();
            entity.setTypography(typography);
        }
        typography.setFontFamily(dto.getFontFamily());
        typography.setFontSize(dto.getFontSize());
        typography.setFontAttributes(dto.getFontAttributes());
        typography.setCharacterSpacing(dto.getCharacterSpacing());
        typography.setLineBreakMode(dto.getLineBreakMode());
        typography.setTextTransform(dto.getTextTransform());
    }

    private void updateLayout(ButtonStyleEntity entity, ButtonStyleUpdateDto dto) {
        ButtonStyleEntity.Layout layout = entity.getLayout();
        if (layout == null) {
            layout = new ButtonStyleEntity.Layout();
            entity.setLayout(layout);
        }
        layout.setPadding(dto.getPadding());
        layout.setMargin(dto.getMargin());
        layout.setHeightRequest(dto.getHeightRequest());
        layout.setWidthRequest(dto.getWidthRequest());
        layout.setMinimumHeightRequest(dto.getMinimumHeightRequest());
        layout.setMinimumWidthRequest(dto.getMinimumWidthRequest());
        layout.setHorizontalOptions(dto.getHorizontalOptions());
        layout.setVerticalOptions(dto.getVerticalOptions());
        layout.setContentLayout(dto.getContentLayout());
    }

    private void updateBorder(ButtonStyleEntity entity, ButtonStyleUpdateDto dto) {
        ButtonStyleEntity.Border border = entity.getBorder();
        if (border == null) {
            border = new ButtonStyleEntity.Border();
            entity.setBorder(border);
        }
        border.setBorderColor(dto.getBorderColor());
        border.setBorderWidth(dto.getBorderWidth());
        border.setCornerRadius(dto.getCornerRadius());
    }

    private void updateAccessibility(ButtonStyleEntity entity, ButtonStyleUpdateDto dto) {
        ButtonStyleEntity.Accessibility accessibility = entity.getAccessibility();
        if (accessibility == null) {
            accessibility = new ButtonStyleEntity.Accessibility();
            entity.setAccessibility(accessibility);
        }
        accessibility.setSemanticDescription(dto.getSemanticDescription());
        accessibility.setSemanticHint(dto.getSemanticHint());
    }

    private void updateShadow(ButtonStyleEntity entity, ButtonStyleUpdateDto dto) {
        // Remove existing shadow if dto.shadow is null
        if (dto.getShadow() == null) {
            entity.setShadow(null);
            return;
        }

        // Create or update shadow
        ButtonShadowEntity shadow = entity.getShadow();
        if (shadow == null) {
            shadow = ButtonShadowEntity.builder()
                    .buttonStyle(entity)
                    .createdAt(OffsetDateTime.now())
                    .build();
            entity.setShadow(shadow);
        }

        shadow.setShadowBrush(dto.getShadow().getShadowBrush());
        shadow.setShadowOpacity(dto.getShadow().getShadowOpacity());
        shadow.setShadowRadius(dto.getShadow().getShadowRadius());
        shadow.setShadowOffsetX(dto.getShadow().getShadowOffset());
    }

    private void updateVisualStates(ButtonStyleEntity existingStyle, List<ButtonStyleUpdateDto.VisualStateUpdateDto> newStates) {
        if (newStates == null) {
            return;
        }

        // Create a map of existing visual states by name for quick lookup
        Map<String, ButtonVisualStateEntity> existingStatesMap = existingStyle.getVisualStates()
                .stream()
                .collect(Collectors.toMap(ButtonVisualStateEntity::getName, Function.identity()));

        // Clear the list but keep the entities for update
        existingStyle.getVisualStates().clear();

        for (ButtonStyleUpdateDto.VisualStateUpdateDto stateDto : newStates) {
            ButtonVisualStateEntity stateEntity = existingStatesMap.get(stateDto.getName());

            if (stateEntity == null) {
                // Create new visual state
                stateEntity = ButtonVisualStateEntity.builder()
                        .buttonStyle(existingStyle)
                        .name(stateDto.getName())
                        .build();
            }

            // Update properties
            stateEntity.setOpacity(stateDto.getOpacity());
            stateEntity.setTextColor(stateDto.getTextColor());
            stateEntity.setBackgroundColor(stateDto.getBackgroundColor());
            stateEntity.setBorderColor(stateDto.getBorderColor());

            // Update shadow
            if (stateDto.getShadow() != null) {
                ButtonVisualStateShadowEntity shadowEntity = stateEntity.getShadow();
                if (shadowEntity == null) {
                    shadowEntity = ButtonVisualStateShadowEntity.builder()
                            .visualSateGroup(stateEntity)
                            .createdAt(OffsetDateTime.now())
                            .build();
                    stateEntity.setShadow(shadowEntity);
                }
                shadowEntity.setShadowBrush(stateDto.getShadow().getShadowBrush());
                shadowEntity.setShadowOpacity(stateDto.getShadow().getShadowOpacity());
                shadowEntity.setShadowRadius(stateDto.getShadow().getShadowRadius());
                shadowEntity.setShadowOffsetX(stateDto.getShadow().getShadowOffset());
            } else if (stateEntity.getShadow() != null) {
                stateEntity.setShadow(null); // Remove shadow if not provided
            }

            existingStyle.getVisualStates().add(stateEntity);
        }
    }

    // Mapping methods
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
                .fontFamily(entity.getTypography() != null ? entity.getTypography().getFontFamily() : brand.getFontFamily())
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
