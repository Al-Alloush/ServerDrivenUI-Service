package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.border;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;

/**
 * Shadow configuration for a .NET MAUI cross-platform border style.
 * <p>
 * Maps to the <Shadow> element inside the Border Style:
 *   <Shadow Brush="{AppThemeBinding Light=#3d516b, Dark=#f5f5f5}" Opacity="0.3" Radius="3" Offset="-0.3,0.3" />
 */
@Entity
@Table(name = "dotnetmaui_crossplatform_border_shadow")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorderShadowEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(length = 36)
    private String id;

    /**
     * Parent border style that owns this shadow configuration.
     * One-to-one association.
     */
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "border_style_id", nullable = false, unique = true)
    private BorderStyleEntity borderStyle;

    @Column(name = "shadow_brush", length = 128)
    private String shadowBrush;

    @Column(name = "shadow_opacity")
    private Float shadowOpacity;

    @Column(name = "shadow_radius")
    private Float shadowRadius;

    /**
     * Shadow offset in "x,y" format.
     */
    @Column(name = "shadow_offset", length = 50)
    private String shadowOffset;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;
}

