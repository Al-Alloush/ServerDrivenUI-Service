package dev.codexo.app.srv.serverdrivenui.model.entity.style.react;

import dev.codexo.app.srv.serverdrivenui.model.entity.ProjectPlatformEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;

/**
 * Platform-specific button style for React Web.
 * Uses the same token-based inheritance concept as the MAUI button style.
 */
@Entity
@Table(
        name = "react_button_style",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_react_button_style_projectplatform_stylekey",
                        columnNames = {"project_platform_id", "style_key"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReactButtonStyleEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_platform_id", nullable = false)
    private ProjectPlatformEntity projectPlatform;

    @Column(name = "style_key", nullable = false, length = 100)
    private String styleKey; // e.g. "PrimaryButton"

    // Tokens + custom colors (to be resolved against BrandIdentity)

    @Column(name = "background_color_token", nullable = false, length = 32)
    private String backgroundColorToken;

    @Column(name = "background_color_custom", length = 16)
    private String backgroundColorCustom;

    @Column(name = "text_color_token", nullable = false, length = 32)
    private String textColorToken;

    @Column(name = "text_color_custom", length = 16)
    private String textColorCustom;

    @Column(name = "border_color_token", length = 32)
    private String borderColorToken;

    @Column(name = "border_color_custom", length = 16)
    private String borderColorCustom;

    @Column(name = "border_width")
    private Integer borderWidth;

    /**
     * CSS-like border radius (React), but semantically same as corner radius.
     */
    @Column(name = "border_radius")
    private Integer borderRadius;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;
}
