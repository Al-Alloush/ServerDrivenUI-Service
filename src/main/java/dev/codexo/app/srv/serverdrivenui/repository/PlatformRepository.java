package dev.codexo.app.srv.serverdrivenui.repository;

import dev.codexo.app.srv.serverdrivenui.model.entity.PlatformEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for target platforms (.NET MAUI, React, ...).
 */
public interface PlatformRepository extends JpaRepository<PlatformEntity, String> {

    /**
     * Find a platform by its unique code, e.g. "DOTNET_MAUI".
     */
    Optional<PlatformEntity> findByCode(String code);
}
