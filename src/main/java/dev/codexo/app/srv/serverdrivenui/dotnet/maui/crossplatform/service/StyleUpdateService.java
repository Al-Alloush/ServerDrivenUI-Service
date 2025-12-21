package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.service;

import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.button.DotnetMauiButtonStyleDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.button.DotnetMauiButtonStyleUpdateDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.button.ButtonShadowEntity;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.button.ButtonStyleEntity;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.button.ButtonVisualStateEntity;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.button.ButtonVisualStateShadowEntity;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.repository.ButtonStyleRepository;
import dev.codexo.app.srv.serverdrivenui.model.entity.BrandIdentityEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StyleUpdateService {

    private final ButtonStyleRepository buttonStyleRepository;

    public StyleUpdateService(ButtonStyleRepository buttonStyleRepository) {
        this.buttonStyleRepository = buttonStyleRepository;
    }

    @Transactional
    public DotnetMauiButtonStyleDto updateButtonStyle(
            String buttonId,
            DotnetMauiButtonStyleUpdateDto updateDto
    ) {
        ButtonStyleEntity entity = buttonStyleRepository
                .findById(buttonId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Button style not found with id: " + buttonId
                ));

        // Update style key
        if (updateDto.getKey() != null) {
            entity.setStyleKey(updateDto.getKey());
        }

        // Update Appearance
        ButtonStyleEntity.Appearance appearance = entity.getAppearance();
        if (appearance == null) {
            appearance = new ButtonStyleEntity.Appearance();
            entity.setAppearance(appearance);
        }
        if (updateDto.getText() != null) appearance.setText(updateDto.getText());
        if (updateDto.getTextColor() != null) appearance.setTextColor(updateDto.getTextColor());
        if (updateDto.getBackgroundColor() != null) appearance.setBackgroundColor(updateDto.getBackgroundColor());
        if (updateDto.getOpacity() != null) appearance.setOpacity(updateDto.getOpacity());
        if (updateDto.getIsVisible() != null) appearance.setVisible(updateDto.getIsVisible());
        if (updateDto.getIsEnabled() != null) appearance.setEnabled(updateDto.getIsEnabled());

        // Update Typography
        ButtonStyleEntity.Typography typography = entity.getTypography();
        if (typography == null) {
            typography = new ButtonStyleEntity.Typography();
            entity.setTypography(typography);
        }
        if (updateDto.getFontFamily() != null) typography.setFontFamily(updateDto.getFontFamily());
        if (updateDto.getFontSize() != null) typography.setFontSize(updateDto.getFontSize());
        if (updateDto.getFontAttributes() != null) typography.setFontAttributes(updateDto.getFontAttributes());
        if (updateDto.getCharacterSpacing() != null) typography.setCharacterSpacing(updateDto.getCharacterSpacing());
        if (updateDto.getLineBreakMode() != null) typography.setLineBreakMode(updateDto.getLineBreakMode());
        if (updateDto.getTextTransform() != null) typography.setTextTransform(updateDto.getTextTransform());

        // Update Layout
        ButtonStyleEntity.Layout layout = entity.getLayout();
        if (layout == null) {
            layout = new ButtonStyleEntity.Layout();
            entity.setLayout(layout);
        }
        if (updateDto.getPadding() != null) layout.setPadding(updateDto.getPadding());
        if (updateDto.getMargin() != null) layout.setMargin(updateDto.getMargin());
        if (updateDto.getHeightRequest() != null) layout.setHeightRequest(updateDto.getHeightRequest());
        if (updateDto.getWidthRequest() != null) layout.setWidthRequest(updateDto.getWidthRequest());
        if (updateDto.getMinimumHeightRequest() != null) layout.setMinimumHeightRequest(updateDto.getMinimumHeightRequest());
        if (updateDto.getMinimumWidthRequest() != null) layout.setMinimumWidthRequest(updateDto.getMinimumWidthRequest());
        if (updateDto.getHorizontalOptions() != null) layout.setHorizontalOptions(updateDto.getHorizontalOptions());
        if (updateDto.getVerticalOptions() != null) layout.setVerticalOptions(updateDto.getVerticalOptions());
        if (updateDto.getContentLayout() != null) layout.setContentLayout(updateDto.getContentLayout());

        // Update Border
        ButtonStyleEntity.Border border = entity.getBorder();
        if (border == null) {
            border = new ButtonStyleEntity.Border();
            entity.setBorder(border);
        }
        if (updateDto.getBorderColor() != null) border.setBorderColor(updateDto.getBorderColor());
        if (updateDto.getBorderWidth() != null) border.setBorderWidth(updateDto.getBorderWidth());
        if (updateDto.getCornerRadius() != null) border.setCornerRadius(updateDto.getCornerRadius());

        // Update Image
        if (updateDto.getImageSource() != null) {
            entity.setImageSource(updateDto.getImageSource());
        }

        // Update Accessibility
        ButtonStyleEntity.Accessibility accessibility = entity.getAccessibility();
        if (accessibility == null) {
            accessibility = new ButtonStyleEntity.Accessibility();
            entity.setAccessibility(accessibility);
        }
        if (updateDto.getSemanticDescription() != null) accessibility.setSemanticDescription(updateDto.getSemanticDescription());
        if (updateDto.getSemanticHint() != null) accessibility.setSemanticHint(updateDto.getSemanticHint());

        ButtonStyleEntity saved = buttonStyleRepository.save(entity);


        // Convert to DTO before returning
        return mapToDto(saved, saved.getTheme().getProjectPlatform().getProject().getBrandIdentity());
    }

    // Add the same mapping methods from StyleQueryService
    private DotnetMauiButtonStyleDto mapToDto(ButtonStyleEntity entity, BrandIdentityEntity brand) {
        return DotnetMauiButtonStyleDto.builder()
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

    private DotnetMauiButtonStyleDto.ShadowDto mapShadowToDto(ButtonShadowEntity shadow) {
        if (shadow == null) return null;
        return DotnetMauiButtonStyleDto.ShadowDto.builder()
                .shadowBrush(shadow.getShadowBrush())
                .shadowOpacity(shadow.getShadowOpacity())
                .shadowRadius(shadow.getShadowRadius())
                .shadowOffset(shadow.getShadowOffsetX())
                .build();
    }

    private DotnetMauiButtonStyleDto.VisualStateDto mapVisualStateToDto(ButtonVisualStateEntity state) {
        return DotnetMauiButtonStyleDto.VisualStateDto.builder()
                .name(state.getName())
                .opacity(state.getOpacity())
                .textColor(state.getTextColor())
                .backgroundColor(state.getBackgroundColor())
                .borderColor(state.getBorderColor())
                .shadow(mapVisualStateShadowToDto(state.getShadow()))
                .build();
    }

    private DotnetMauiButtonStyleDto.ShadowDto mapVisualStateShadowToDto(ButtonVisualStateShadowEntity shadow) {
        if (shadow == null) return null;
        return DotnetMauiButtonStyleDto.ShadowDto.builder()
                .shadowBrush(shadow.getShadowBrush())
                .shadowOpacity(shadow.getShadowOpacity())
                .shadowRadius(shadow.getShadowRadius())
                .shadowOffset(shadow.getShadowOffsetX())
                .build();
    }
}

