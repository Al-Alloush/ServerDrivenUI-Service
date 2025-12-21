package dev.codexo.app.srv.serverdrivenui.model.entity;

import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.label.LabelStyleEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Connects a Project with a specific Platform (e.g. "iMeterRecorder" + ".NET MAUI").
 * All platform-specific style tables hang off this entity.
 */
@Entity
@Table(
        name = "project_platform",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_project_platform_projectid_platformid",
                        columnNames = {"project_id", "platform_id"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectPlatformEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "platform_id", nullable = false)
    private PlatformEntity platform;

    @Column(name = "is_active", nullable = false)
    private boolean active;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;


    @OneToMany(mappedBy = "projectPlatform", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<LabelStyleEntity> dotnetMauiLabelStyles;

}
