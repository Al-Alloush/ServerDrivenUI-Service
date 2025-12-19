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
        return DotnetMauiButtonStyleDto.builder()
                .key(entity.getStyleKey())
                .background(resolveColor(entity.getBackgroundColorToken(),
                        entity.getBackgroundColorCustom(),
                        brand))
                .textColor(resolveColor(entity.getTextColorToken(),
                        entity.getTextColorCustom(),
                        brand))
                .borderColor(resolveColor(entity.getBorderColorToken(),
                        entity.getBorderColorCustom(),
                        brand))
                .cornerRadius(entity.getCornerRadius())
                .borderWidth(entity.getBorderWidth())
                .build();
    }

    /**
     * Resolve a symbolic color token against the brand identity.
     *
     * @param token  e.g. PRIMARY, SECONDARY, TERTIARY, CUSTOM
     * @param custom hex color used when token == CUSTOM
     * @param brand  project's brand identity
     * @return resolved hex color, or {@code null} if nothing usable is found
     */
    private static String resolveColor(
            String token,
            String custom,
            BrandIdentityEntity brand
    ) {
        if (token == null || token.isBlank()) {
            return custom;
        }

        return switch (token.toUpperCase()) {
            case "PRIMARY"   -> brand.getPrimaryColor();
            case "SECONDARY" -> brand.getSecondaryColor();
            case "TERTIARY"  -> brand.getTertiaryColor();
            case "CUSTOM"    -> custom;
            default          -> custom;
        };
    }
}
