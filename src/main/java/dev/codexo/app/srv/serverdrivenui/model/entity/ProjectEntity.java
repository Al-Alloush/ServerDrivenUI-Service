package dev.codexo.app.srv.serverdrivenui.model.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * A styled application owned by a user (e.g. "iMeterRecorder Mobile").
 * Each project has a single BrandIdentity and can be enabled
 * on multiple platforms (ProjectPlatform).
 */
@Entity
@Table(name = "project")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(length = 36)
    private String id;

    /** Owner of the project. */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity owner;

    @Column(nullable = false)
    private String name;

    /**
     * Optional slug (project code) for nicer URLs. Unique per user or globally,
     * depending on how you want to enforce it (DB constraint is flexible).
     */
    @Column()
    private String slug;

    @Column(columnDefinition = "text")
    private String description;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @Column(name = "is_deleted", nullable = false)
    private boolean deleted;

    /** The project's main brand identity (1:1). */
    @OneToOne(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private BrandIdentityEntity brandIdentity;

    /** Platforms this project is enabled on (MAUI, React, ...). */
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProjectPlatformEntity> platforms;
}
