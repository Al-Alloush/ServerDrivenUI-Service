package dev.codexo.app.srv.serverdrivenui.model.entity.multitenant;

import dev.codexo.app.srv.serverdrivenui.model.entity.ProjectPlatformEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;

@Entity
@Table(name = "project_api_key")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectApiKeyEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_platform_id", nullable = false)
    private ProjectPlatformEntity projectPlatform;

    @Column(name = "api_key", nullable = false, unique = true, length = 64)
    private String apiKey; // e.g., "pk_live_abc123def456"

    @Column(name = "is_active", nullable = false)
    private boolean active;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "expires_at")
    private OffsetDateTime expiresAt;

    @Column(name = "last_used_at")
    private OffsetDateTime lastUsedAt;
}
