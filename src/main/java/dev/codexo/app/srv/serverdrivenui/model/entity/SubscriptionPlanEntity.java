package dev.codexo.app.srv.serverdrivenui.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * A subscription plan definition (e.g. FREE_1M, FREE_3M, PRO, ...).
 * Holds the business limits and default duration.
 */
@Entity
@Table(name = "subscription_plan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionPlanEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(length = 36)
    private String id;

    /**
     * Short unique code used in business logic and UI, e.g. "FREE_1M", "PRO".
     */
    @Column(nullable = false, unique = true, length = 50)
    private String code;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    /**
     * Max number of projects a user can own on this plan.
     * Null means "no explicit limit".
     */
    @Column(name = "max_projects")
    private Integer maxProjects;

    /**
     * Max number of platforms per project on this plan.
     * Null means "no explicit limit".
     */
    @Column(name = "max_platforms_per_project")
    private Integer maxPlatformsPerProject;

    @Column(name = "monthly_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal monthlyPrice;

    /**
     * Whether this plan is the default for newly registered users.
     */
    @Column(name = "is_default", nullable = false)
    private boolean defaultPlan;

    /**
     * Default subscription duration in days.
     * Example: 30 → free for 1 month, 90 → free for 3 months.
     * Null means "unlimited duration".
     */
    @Column(name = "default_duration_days")
    private Integer defaultDurationDays;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @OneToMany(mappedBy = "plan", fetch = FetchType.LAZY)
    private List<UserSubscriptionEntity> subscriptions;
}
