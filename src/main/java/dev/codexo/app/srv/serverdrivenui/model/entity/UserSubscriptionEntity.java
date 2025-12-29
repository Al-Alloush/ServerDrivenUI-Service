package dev.codexo.app.srv.serverdrivenui.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;

/**
 * Concrete subscription instance of a user on a given plan.
 * Supports history (past, cancelled, expired) as well as active subscriptions.
 */
@Entity
@Table(name = "user_subscription")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSubscriptionEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subscription_plan_id", nullable = false)
    private SubscriptionPlanEntity plan;

    /**
     * Subscription status, e.g. ACTIVE, CANCELLED, EXPIRED.
     * You can later replace this with an enum if you like.
     */
    @Column(nullable = false, length = 32)
    private String status;

    @Column(name = "started_at", nullable = false)
    private OffsetDateTime startedAt;

    /**
     * Planned / actual expiry date.
     * For time-limited free plans, this is calculated from
     * startedAt + plan.defaultDurationDays.
     */
    @Column(name = "expires_at")
    private OffsetDateTime expiresAt;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;
}
