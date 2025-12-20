package dev.codexo.app.srv.serverdrivenui.service;

import dev.codexo.app.srv.serverdrivenui.model.dto.dotnetmaui.DotnetMauiButtonStyleDto;
import dev.codexo.app.srv.serverdrivenui.model.dto.dotnetmaui.DotnetMauiButtonThemeWrapperDto;
import dev.codexo.app.srv.serverdrivenui.model.entity.BrandIdentityEntity;
import dev.codexo.app.srv.serverdrivenui.model.entity.PlatformEntity;
import dev.codexo.app.srv.serverdrivenui.model.entity.ProjectEntity;
import dev.codexo.app.srv.serverdrivenui.model.entity.ProjectPlatformEntity;
import dev.codexo.app.srv.serverdrivenui.model.entity.style.dotnetmaui.crossplatform.button.DotnetMauiCrossPlatformButtonStyleEntity;
import dev.codexo.app.srv.serverdrivenui.repository.DotnetMauiCrossPlatformButtonStyleRepository;
import dev.codexo.app.srv.serverdrivenui.repository.PlatformRepository;
import dev.codexo.app.srv.serverdrivenui.repository.ProjectPlatformRepository;
import dev.codexo.app.srv.serverdrivenui.repository.ProjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Read-only service that prepares .NET MAUI styles for a project.
 * <p>
 * Workflow:
 *  1. Load project by slug.
 *  2. Load .NET MAUI platform entity.
 *  3. Resolve ProjectPlatform relation.
 *  4. Use BrandIdentity + ButtonStyle entities to build the payload
 *     expected by the MAUI client.
 */
@Service
@Transactional(readOnly = true)
public class DotnetMauiStyleQueryService {

    /**
     * Code stored in {@link PlatformEntity#getCode()} for .NET MAUI cross-platform.
     */
    public static final String DOTNET_MAUI_PLATFORM_CODE = "DOTNET_MAUI";

    private final ProjectRepository projectRepository;
    private final PlatformRepository platformRepository;
    private final ProjectPlatformRepository projectPlatformRepository;
    private final DotnetMauiCrossPlatformButtonStyleRepository buttonStyleRepository;

    public DotnetMauiStyleQueryService(ProjectRepository projectRepository, PlatformRepository platformRepository, ProjectPlatformRepository projectPlatformRepository, DotnetMauiCrossPlatformButtonStyleRepository buttonStyleRepository) {
        this.projectRepository = projectRepository;
        this.platformRepository = platformRepository;
        this.projectPlatformRepository = projectPlatformRepository;
        this.buttonStyleRepository = buttonStyleRepository;
    }

    /**
     * Returns all .NET MAUI button styles for the given project slug.
     * <p>
     * This method is used by the controller behind
     *   GET /imeterrecorder/style/buttons
     */
    public DotnetMauiButtonThemeWrapperDto getButtonStylesForProjectSlug(String projectSlug) {
        // 1) Project
        ProjectEntity project = projectRepository
                .findBySlugAndDeletedFalse(projectSlug)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Project not found or deleted: " + projectSlug));

        // 2) Platform
        PlatformEntity platform = platformRepository
                .findByCode(DOTNET_MAUI_PLATFORM_CODE)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Platform not found: " + DOTNET_MAUI_PLATFORM_CODE));

        // 3) ProjectPlatform
        ProjectPlatformEntity projectPlatform = projectPlatformRepository
                .findByProjectAndPlatform(project, platform)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Project " + projectSlug + " is not enabled for platform " + DOTNET_MAUI_PLATFORM_CODE));

        if (!projectPlatform.isActive()) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Platform " + DOTNET_MAUI_PLATFORM_CODE + " is disabled for project " + projectSlug);
        }

        BrandIdentityEntity brand = project.getBrandIdentity();
        if (brand == null) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Project " + projectSlug + " does not have a BrandIdentity configured.");
        }

        // 4) Button style entities
        List<DotnetMauiCrossPlatformButtonStyleEntity> entities =
                buttonStyleRepository.findByProjectPlatform(projectPlatform);

        List<DotnetMauiButtonStyleDto> styles = entities.stream()
                .map(e -> mapToDto(e, brand))
                .toList();

        // For the demo we return the same styles for light and dark
        return DotnetMauiButtonThemeWrapperDto.builder()
                .light(styles)
                .dark(styles)
                .build();
    }

    private static DotnetMauiButtonStyleDto mapToDto(
            DotnetMauiCrossPlatformButtonStyleEntity entity,
            BrandIdentityEntity brand
    ) {
        // Map appearance
        DotnetMauiCrossPlatformButtonStyleEntity.Appearance appearance = entity.getAppearance();
        DotnetMauiCrossPlatformButtonStyleEntity.Typography typography = entity.getTypography();
        DotnetMauiCrossPlatformButtonStyleEntity.Layout layout = entity.getLayout();
        DotnetMauiCrossPlatformButtonStyleEntity.Border border = entity.getBorder();
        DotnetMauiCrossPlatformButtonStyleEntity.Accessibility accessibility = entity.getAccessibility();

        // Map shadow
        DotnetMauiButtonStyleDto.ShadowDto shadowDto = null;
        if (entity.getShadow() != null) {
            shadowDto = DotnetMauiButtonStyleDto.ShadowDto.builder()
                    .shadowBrush(entity.getShadow().getShadowBrush())
                    .shadowOpacity(entity.getShadow().getShadowOpacity())
                    .shadowRadius(entity.getShadow().getShadowRadius())
                    .shadowOffset(entity.getShadow().getShadowOffsetX())
                    .build();
        }

        // Map visual states
        List<DotnetMauiButtonStyleDto.VisualStateDto> visualStates = null;
        if (entity.getVisualStates() != null) {
            visualStates = entity.getVisualStates().stream()
                    .map(vs -> {
                        DotnetMauiButtonStyleDto.ShadowDto vsShadow = null;
                        if (vs.getShadow() != null) {
                            vsShadow = DotnetMauiButtonStyleDto.ShadowDto.builder()
                                    .shadowBrush(vs.getShadow().getShadowBrush())
                                    .shadowOpacity(vs.getShadow().getShadowOpacity())
                                    .shadowRadius(vs.getShadow().getShadowRadius())
                                    .shadowOffset(vs.getShadow().getShadowOffsetX())
                                    .build();
                        }

                        return DotnetMauiButtonStyleDto.VisualStateDto.builder()
                                .name(vs.getName())
                                .opacity(vs.getOpacity())
                                .textColor(vs.getTextColor())
                                .backgroundColor(vs.getBackgroundColor())
                                .borderColor(vs.getBorderColor())
                                .shadow(vsShadow)
                                .build();
                    })
                    .toList();
        }

        return DotnetMauiButtonStyleDto.builder()
                .key(entity.getStyleKey())
                // Appearance
                .text(appearance != null ? appearance.getText() : null)
                .textColor(appearance != null ? appearance.getTextColor() : null)
                .backgroundColor(appearance != null ? appearance.getBackgroundColor() : null)
                .opacity(appearance != null ? appearance.getOpacity() : null)
                .isVisible(appearance != null ? appearance.isVisible() : null)
                .isEnabled(appearance != null ? appearance.isEnabled() : null)
                // Typography
                .fontFamily(typography != null ? typography.getFontFamily() : null)
                .fontSize(typography != null ? typography.getFontSize() : null)
                .fontAttributes(typography != null ? typography.getFontAttributes() : null)
                .characterSpacing(typography != null ? typography.getCharacterSpacing() : null)
                .lineBreakMode(typography != null ? typography.getLineBreakMode() : null)
                .textTransform(typography != null ? typography.getTextTransform() : null)
                // Layout
                .padding(layout != null ? layout.getPadding() : null)
                .margin(layout != null ? layout.getMargin() : null)
                .heightRequest(layout != null ? layout.getHeightRequest() : null)
                .widthRequest(layout != null ? layout.getWidthRequest() : null)
                .minimumHeightRequest(layout != null ? layout.getMinimumHeightRequest() : null)
                .minimumWidthRequest(layout != null ? layout.getMinimumWidthRequest() : null)
                .horizontalOptions(layout != null ? layout.getHorizontalOptions() : null)
                .verticalOptions(layout != null ? layout.getVerticalOptions() : null)
                .contentLayout(layout != null ? layout.getContentLayout() : null)
                // Border
                .borderColor(border != null ? border.getBorderColor() : null)
                .borderWidth(border != null ? border.getBorderWidth() : null)
                .cornerRadius(border != null ? border.getCornerRadius() : null)
                // Image
                .imageSource(entity.getImageSource())
                // Shadow
                .shadow(shadowDto)
                // Accessibility
                .semanticDescription(accessibility != null ? accessibility.getSemanticDescription() : null)
                .semanticHint(accessibility != null ? accessibility.getSemanticHint() : null)
                // Visual states
                .visualStates(visualStates)
                .build();
    }



}
