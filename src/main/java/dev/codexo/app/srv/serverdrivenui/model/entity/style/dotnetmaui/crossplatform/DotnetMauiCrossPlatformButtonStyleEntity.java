package dev.codexo.app.srv.serverdrivenui.model.entity.style.dotnetmaui.crossplatform;

import dev.codexo.app.srv.serverdrivenui.model.entity.ProjectPlatformEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;

/**
 * Platform-specific button style for .NET MAUI.
 * Inherits brand colors via tokens that are resolved against BrandIdentity.
 */
@Entity
@Table(
        name = "dotnet_maui_button_style",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_maui_button_style_projectplatform_stylekey",
                        columnNames = {"project_platform_id", "style_key"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DotnetMauiCrossPlatformButtonStyleEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(length = 36)
    private String id;

    /** Project + platform this style belongs to. */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_platform_id", nullable = false)
    private ProjectPlatformEntity projectPlatform;

    /**
     * Style key used by the client (e.g. "PrimaryButton").
     * Must be unique per project_platform.
     */
    @Column(name = "style_key", nullable = false, length = 100)
    private String styleKey;

    // ---------- Color tokens & custom values ----------

    /**
     * How to resolve the background color:
     * PRIMARY, SECONDARY, TERTIARY, CUSTOM, ...
     */
    @Column(name = "background_color_token", nullable = false, length = 32)
    private String backgroundColorToken;

    /**
     * Custom background color (hex) used when token == "CUSTOM".
     */
    @Column(name = "background_color_custom", length = 16)
    private String backgroundColorCustom;

    /**
     * How to resolve the text color:
     * PRIMARY, SECONDARY, TERTIARY, CUSTOM, ...
     */
    @Column(name = "text_color_token", nullable = false, length = 32)
    private String textColorToken;

    /**
     * Custom text color (hex) used when token == "CUSTOM".
     */
    @Column(name = "text_color_custom", length = 16)
    private String textColorCustom;

    /**
     * How to resolve the border color (optional).
     */
    @Column(name = "border_color_token", length = 32)
    private String borderColorToken;

    @Column(name = "border_color_custom", length = 16)
    private String borderColorCustom;

    // --------------------------------------------------

    @Column(name = "corner_radius")
    private Integer cornerRadius;

    @Column(name = "border_width")
    private Integer borderWidth;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;
}
