package dev.codexo.app.srv.serverdrivenui.repository;

import dev.codexo.app.srv.serverdrivenui.model.entity.ProjectPlatformEntity;
import dev.codexo.app.srv.serverdrivenui.model.entity.style.dotnetmaui.crossplatform.button.DotnetMauiCrossPlatformButtonStyleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for .NET MAUI cross-platform button styles.
 */
public interface DotnetMauiCrossPlatformButtonStyleRepository
        extends JpaRepository<DotnetMauiCrossPlatformButtonStyleEntity, String> {

    /**
     * All button styles defined for a given project + platform.
     */
    List<DotnetMauiCrossPlatformButtonStyleEntity> findByProjectPlatform(ProjectPlatformEntity projectPlatform);
}
