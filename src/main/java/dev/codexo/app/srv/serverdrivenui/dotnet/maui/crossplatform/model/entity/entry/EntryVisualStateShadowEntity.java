package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.entry;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;

@Entity
@Table(name = "dotnetmaui_crossplatform_entry_visualstate_shadow")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntryVisualStateShadowEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(length = 36)
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entry_visual_status_style_id", nullable = false)
    private EntryVisualStateEntity entryVisualState;

    @Column(name = "shadow_brush", length = 128)
    private String shadowBrush;

    @Column(name = "shadow_opacity")
    private Double shadowOpacity;

    @Column(name = "shadow_radius")
    private Double shadowRadius;

    @Column(name = "shadow_offset", length = 50)
    private String shadowOffset;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;
}

