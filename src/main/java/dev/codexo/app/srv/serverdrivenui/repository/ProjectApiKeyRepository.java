package dev.codexo.app.srv.serverdrivenui.repository;

import dev.codexo.app.srv.serverdrivenui.model.entity.multitenant.ProjectApiKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectApiKeyRepository extends JpaRepository<ProjectApiKeyEntity, String> {

    /**
     * Find an API key by its unique key value.
     */
    Optional<ProjectApiKeyEntity> findByApiKey(String apiKey);

    /**
     * Find active API key by key value and check if it's active.
     */
    Optional<ProjectApiKeyEntity> findByApiKeyAndActiveTrue(String apiKey);
}

