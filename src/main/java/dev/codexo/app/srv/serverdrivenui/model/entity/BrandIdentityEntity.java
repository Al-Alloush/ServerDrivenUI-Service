package dev.codexo.app.srv.serverdrivenui.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;

/**
 * Base branding information for a project.
 * Platform-specific style tables "inherit" from these values
 * by using tokens (PRIMARY, SECONDARY, TERTIARY, BRAND_FONT, etc.).
 */
@Entity
@Table(name = "brand_identity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandIdentityEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(length = 36)
    private String id;

    /** Project this brand configuration belongs to (1:1). */
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false, unique = true)
    private ProjectEntity project;

    @Column(name = "brand_name", nullable = false)
    private String brandName;

    /** Primary brand color (hex, e.g. #26c998). */
    @Column(name = "primary_color", nullable = false, length = 16)
    private String primaryColor;

    /** Secondary brand color (hex). */
    @Column(name = "secondary_color", nullable = false, length = 16)
    private String secondaryColor;

    /** Optional tertiary color used for accents. */
    @Column(name = "tertiary_color", length = 16)
    private String tertiaryColor;

    /** Default font family for the brand (e.g. "Inter", "Open Sans"). */
    @Column(name = "font_family")
    private String fontFamily;

    /** Optional logo URL (could point to S3, CDN, etc.). */
    @Column(name = "logo_url", length = 512)
    private String logoUrl;

    @Column(columnDefinition = "text")
    private String description;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;
}
