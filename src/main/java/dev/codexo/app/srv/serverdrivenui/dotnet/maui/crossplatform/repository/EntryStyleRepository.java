package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.repository;

import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.entry.EntryStyleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EntryStyleRepository extends JpaRepository<EntryStyleEntity, String> {
    List<EntryStyleEntity> findByThemeId(String themeId);
    Optional<EntryStyleEntity> findByThemeIdAndStyleKey(String themeId, String styleKey);
}


