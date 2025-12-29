package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.entry;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;

@Entity
@Table(name = "dotnetmaui_crossplatform_entry_visualstate")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntryVisualStateEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entry_style_id", nullable = false)
    private EntryStyleEntity entryStyle;

    @Column(name = "state_name", nullable = false, length = 50)
    private String stateName; // e.g., "Normal", "Focused", "Disabled"

    @Column(name = "opacity")
    private Double opacity;

    @Column(name = "background_color", length = 128)
    private String backgroundColor;

    @Column(name = "text_color", length = 128)
    private String textColor;

    @OneToOne(mappedBy = "entryVisualState", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private EntryVisualStateShadowEntity shadow;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;
}

