package dev.codexo.app.srv.serverdrivenui.repository;

import dev.codexo.app.srv.serverdrivenui.model.entity.PlatformEntity;
import dev.codexo.app.srv.serverdrivenui.model.entity.ProjectEntity;
import dev.codexo.app.srv.serverdrivenui.model.entity.ProjectPlatformEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Connects projects to platforms (many-to-many).
 */
public interface ProjectPlatformRepository extends JpaRepository<ProjectPlatformEntity, String> {

    /**
     * Returns the configuration for one project on one platform.
     */
    Optional<ProjectPlatformEntity> findByProjectAndPlatform(ProjectEntity project, PlatformEntity platform);
}

