package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.label;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;

@Entity
@Table(name = "dotnetmaui_crossplatform_label_visualstate")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabelVisualStateEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "label_style_id", nullable = false)
    private LabelStyleEntity labelStyle;

    @Column(name = "state_name", nullable = false, length = 50)
    private String stateName; // e.g., "Normal", "Disabled"

    @Column(name = "opacity")
    private Double opacity;

    @Column(name = "text_color", length = 128)
    private String textColor;

    @Column(name = "font_family", length = 128)
    private String fontFamily;

    @Enumerated(EnumType.STRING)
    @Column(name = "font_attributes")
    private FontAttributes fontAttributes;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;
}

