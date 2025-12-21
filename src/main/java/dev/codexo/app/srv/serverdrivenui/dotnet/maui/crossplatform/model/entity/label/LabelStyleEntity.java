package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.label;

import dev.codexo.app.srv.serverdrivenui.model.entity.ProjectPlatformEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;

/**
 * Platform-specific label/text style for .NET MAUI.
 */
@Entity
@Table(
        name = "dotnet_maui_label_style",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_maui_label_style_projectplatform_stylekey",
                        columnNames = {"project_platform_id", "style_key"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabelStyleEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_platform_id", nullable = false)
    private ProjectPlatformEntity projectPlatform;

    @Column(name = "style_key", nullable = false, length = 100)
    private String styleKey; // e.g. "TitleLabel"

    // ---------- Color & font tokens ----------

    @Column(name = "text_color_token", nullable = false, length = 32)
    private String textColorToken;

    @Column(name = "text_color_custom", length = 16)
    private String textColorCustom;

    /**
     * How to resolve font family:
     * BRAND_FONT, CUSTOM, ...
     */
    @Column(name = "font_family_token", nullable = false, length = 32)
    private String fontFamilyToken;

    @Column(name = "font_family_custom")
    private String fontFamilyCustom;

    // -----------------------------------------

    @Column(name = "font_size", nullable = false)
    private Double fontSize;

    @Column(name = "font_weight", length = 32)
    private String fontWeight;

    /** Serialized margin, e.g. "0,4,0,4". */
    @Column(name = "margin", length = 50)
    private String margin;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;
}
