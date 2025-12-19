package dev.codexo.app.srv.serverdrivenui.model.entity.style.dotnetmaui.crossplatform.button;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;

/**
 * Shadow configuration for a .NET MAUI cross-platform button style.
 * <p>
 * Maps to the <Shadow> element inside the base Style:
 *   <Shadow Brush="Black" Opacity="0.3" Radius="8" Offset="0,4" />
 */
@Entity
@Table(name = "dotnet_maui_button_shadow")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DotnetMauiCrossPlatformButtonShadowEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(length = 36)
    private String id;

    /**
     * Parent button style that owns this shadow configuration.
     * One-to-one association.
     */
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "button_style_id", nullable = false, unique = true)
    private DotnetMauiCrossPlatformButtonStyleEntity buttonStyle;

    /**
     * Token for shadow brush color:
     * PRIMARY, SECONDARY, CUSTOM, etc.
     */
    @Column(name = "brush_color_token", length = 32)
    private String brushColorToken;

    /**
     * Custom brush color (hex) when token == "CUSTOM".
     */
    @Column(name = "brush_color_custom", length = 16)
    private String brushColorCustom;

    /**
     * Shadow opacity (0..1).
     */
    @Column(name = "opacity")
    private Double opacity;

    /**
     * Shadow blur radius.
     */
    @Column(name = "radius")
    private Double radius;

    /**
     * Horizontal offset of the shadow.
     * XAML Offset="x,y".
     */
    @Column(name = "offset_x")
    private Double offsetX;

    /**
     * Vertical offset of the shadow.
     */
    @Column(name = "offset_y")
    private Double offsetY;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;
}
