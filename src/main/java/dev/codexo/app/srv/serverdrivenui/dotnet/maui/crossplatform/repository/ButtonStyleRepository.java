package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.repository;

import dev.codexo.app.srv.serverdrivenui.model.entity.PlatformThemeEntity;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.button.ButtonStyleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for .NET MAUI cross-platform button styles.
 */
public interface ButtonStyleRepository
        extends JpaRepository<ButtonStyleEntity, String> {

    /**
     * All button styles for a given theme.
     */
    List<ButtonStyleEntity> findByTheme(PlatformThemeEntity theme);
}
