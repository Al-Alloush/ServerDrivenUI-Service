package dev.codexo.app.srv.serverdrivenui.repository;

import dev.codexo.app.srv.serverdrivenui.model.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Access to projects (applications) registered in the SDUI service.
 */
public interface ProjectRepository extends JpaRepository<ProjectEntity, String> {

    /**
     * Find a project by its unique slug that is not marked as deleted.
     */
    Optional<ProjectEntity> findBySlugAndDeletedFalse(String slug);
}
