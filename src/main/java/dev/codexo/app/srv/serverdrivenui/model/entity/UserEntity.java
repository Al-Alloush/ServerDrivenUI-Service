package dev.codexo.app.srv.serverdrivenui.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;
import java.util.List;
/**
 * Application user, authenticated via Keycloak.
 * We only store the mapping to the Keycloak user and
 * SDUI-specific metadata (projects, subscriptions, etc.).
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(length = 36)
    private String id;

    /**
     * Keycloak user id (subject / sub claim).
     * This should be unique across all users.
     */
    @Column(name = "keycloak_user_id", nullable = false, unique = true, length = 64)
    private String keycloakUserId;

    @Column(nullable = false)
    private String email;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    /** All projects owned by this user. */
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<ProjectEntity> projects;

    /** All subscriptions of this user (historical + current). */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserSubscriptionEntity> subscriptions;
}
