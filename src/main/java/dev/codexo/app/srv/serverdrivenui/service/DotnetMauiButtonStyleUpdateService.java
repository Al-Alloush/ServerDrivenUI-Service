package dev.codexo.app.srv.serverdrivenui.service;

import dev.codexo.app.srv.serverdrivenui.model.dto.dotnetmaui.DotnetMauiButtonStyleUpdateDto;
import dev.codexo.app.srv.serverdrivenui.model.entity.style.dotnetmaui.crossplatform.button.DotnetMauiCrossPlatformButtonStyleEntity;
import dev.codexo.app.srv.serverdrivenui.repository.DotnetMauiCrossPlatformButtonStyleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DotnetMauiButtonStyleUpdateService {

    private final DotnetMauiCrossPlatformButtonStyleRepository buttonStyleRepository;

    public DotnetMauiButtonStyleUpdateService(DotnetMauiCrossPlatformButtonStyleRepository buttonStyleRepository) {
        this.buttonStyleRepository = buttonStyleRepository;
    }

    @Transactional
    public DotnetMauiCrossPlatformButtonStyleEntity updateButtonStyle(
            String buttonId,
            DotnetMauiButtonStyleUpdateDto updateDto
    ) {
        DotnetMauiCrossPlatformButtonStyleEntity entity = buttonStyleRepository
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
        DotnetMauiCrossPlatformButtonStyleEntity.Appearance appearance = entity.getAppearance();
        if (appearance == null) {
            appearance = new DotnetMauiCrossPlatformButtonStyleEntity.Appearance();
            entity.setAppearance(appearance);
        }
        if (updateDto.getText() != null) appearance.setText(updateDto.getText());
        if (updateDto.getTextColor() != null) appearance.setTextColor(updateDto.getTextColor());
        if (updateDto.getBackgroundColor() != null) appearance.setBackgroundColor(updateDto.getBackgroundColor());
        if (updateDto.getOpacity() != null) appearance.setOpacity(updateDto.getOpacity());
        if (updateDto.getIsVisible() != null) appearance.setVisible(updateDto.getIsVisible());
        if (updateDto.getIsEnabled() != null) appearance.setEnabled(updateDto.getIsEnabled());

        // Update Typography
        DotnetMauiCrossPlatformButtonStyleEntity.Typography typography = entity.getTypography();
        if (typography == null) {
            typography = new DotnetMauiCrossPlatformButtonStyleEntity.Typography();
            entity.setTypography(typography);
        }
        if (updateDto.getFontFamily() != null) typography.setFontFamily(updateDto.getFontFamily());
        if (updateDto.getFontSize() != null) typography.setFontSize(updateDto.getFontSize());
        if (updateDto.getFontAttributes() != null) typography.setFontAttributes(updateDto.getFontAttributes());
        if (updateDto.getCharacterSpacing() != null) typography.setCharacterSpacing(updateDto.getCharacterSpacing());
        if (updateDto.getLineBreakMode() != null) typography.setLineBreakMode(updateDto.getLineBreakMode());
        if (updateDto.getTextTransform() != null) typography.setTextTransform(updateDto.getTextTransform());

        // Update Layout
        DotnetMauiCrossPlatformButtonStyleEntity.Layout layout = entity.getLayout();
        if (layout == null) {
            layout = new DotnetMauiCrossPlatformButtonStyleEntity.Layout();
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
        DotnetMauiCrossPlatformButtonStyleEntity.Border border = entity.getBorder();
        if (border == null) {
            border = new DotnetMauiCrossPlatformButtonStyleEntity.Border();
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
        DotnetMauiCrossPlatformButtonStyleEntity.Accessibility accessibility = entity.getAccessibility();
        if (accessibility == null) {
            accessibility = new DotnetMauiCrossPlatformButtonStyleEntity.Accessibility();
            entity.setAccessibility(accessibility);
        }
        if (updateDto.getSemanticDescription() != null) accessibility.setSemanticDescription(updateDto.getSemanticDescription());
        if (updateDto.getSemanticHint() != null) accessibility.setSemanticHint(updateDto.getSemanticHint());

        return buttonStyleRepository.save(entity);
    }
}

