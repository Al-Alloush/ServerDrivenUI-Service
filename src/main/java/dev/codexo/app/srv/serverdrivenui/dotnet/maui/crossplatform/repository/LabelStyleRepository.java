package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.repository;

import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.label.LabelStyleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LabelStyleRepository extends JpaRepository<LabelStyleEntity, String> {
    List<LabelStyleEntity> findByThemeId(String themeId);
    Optional<LabelStyleEntity> findByThemeIdAndKey(String themeId, String key);
}


