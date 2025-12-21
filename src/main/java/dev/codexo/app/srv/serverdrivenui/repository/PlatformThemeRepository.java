package dev.codexo.app.srv.serverdrivenui.repository;

import dev.codexo.app.srv.serverdrivenui.model.entity.PlatformThemeEntity;
import dev.codexo.app.srv.serverdrivenui.model.entity.ProjectPlatformEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlatformThemeRepository extends JpaRepository<PlatformThemeEntity, String> {

    List<PlatformThemeEntity> findByProjectPlatform(ProjectPlatformEntity projectPlatform);

    Optional<PlatformThemeEntity> findByProjectPlatformAndThemeName(
            ProjectPlatformEntity projectPlatform,
            String themeName
    );
}
