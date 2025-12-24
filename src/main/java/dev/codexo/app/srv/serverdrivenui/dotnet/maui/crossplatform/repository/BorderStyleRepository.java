package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.repository;

import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.border.BorderStyleEntity;
import dev.codexo.app.srv.serverdrivenui.model.entity.PlatformThemeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorderStyleRepository extends JpaRepository<BorderStyleEntity, String> {

    /**
     * Find all border styles for a specific theme.
     */
    List<BorderStyleEntity> findByTheme(PlatformThemeEntity theme);
}

