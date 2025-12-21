package dev.codexo.app.srv.serverdrivenui.model.entity;

import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.button.ButtonStyleEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Represents a UI theme for a project platform.
 * Examples: "Light", "Dark", "High Contrast", "Custom Blue", etc.
 */
@Entity
@Table(
        name = "platform_theme",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_platform_theme_projectplatform_name",
                        columnNames = {"project_platform_id", "theme_name"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlatformThemeEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_platform_id", nullable = false)
    private ProjectPlatformEntity projectPlatform;

    /**
     * Theme name: "Light", "Dark", "HighContrast", "Custom1", etc.
     */
    @Column(name = "theme_name", nullable = false, length = 50)
    private String themeName;

    /**
     * Display name for UI: "Light Theme", "Dark Theme", etc.
     */
    @Column(name = "display_name", length = 100)
    private String displayName;

    /**
     * Is this the default theme for this project platform?
     */
    @Column(name = "is_default", nullable = false)
    private boolean isDefault;

    /**
     * Optional description
     */
    @Column(columnDefinition = "text")
    private String description;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @OneToMany(mappedBy = "theme", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ButtonStyleEntity> buttonStyles;
}
