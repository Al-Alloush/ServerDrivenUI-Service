package dev.codexo.app.srv.serverdrivenui.model.entity.style.dotnetmaui.crossplatform.button;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;

@Entity
@Table(name = "dotnet_maui_button_visual_state_shadow")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DotnetMauiCrossPlatformButtonVisualStateShadowEntity {

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
    @JoinColumn(name = "button_visual_status_style_id", nullable = false, unique = true)
    private DotnetMauiCrossPlatformButtonVisualStateEntity visualSateGroup;

    @Column(name = "shadow_brush", length = 128)
    private String shadowBrush;

    @Column(name = "shadow_opacity")
    private Float shadowOpacity;

    @Column(name = "shadow_radius")
    private Float shadowRadius;
    /**
     * Shadow offset in "x,y" format.
     */
    @Column(name = "shadow_offset")
    private String shadowOffsetX;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;
}
