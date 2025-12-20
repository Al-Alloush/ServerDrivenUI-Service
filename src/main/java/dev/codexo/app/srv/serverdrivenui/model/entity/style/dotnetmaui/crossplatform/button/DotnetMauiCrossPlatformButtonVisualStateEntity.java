package dev.codexo.app.srv.serverdrivenui.model.entity.style.dotnetmaui.crossplatform.button;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Visual state override for a .NET MAUI cross-platform button.
 * <p>
 * Represents one <VisualState x:Name="..."> block (Normal, Disabled,
 * PointerOver, Pressed, ...). Each row contains only the properties
 * that can change per state: opacity, colors, border, scale, shadow.
 */
@Entity
@Table(
        name = "dotnet_maui_button_visual_state",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_maui_button_state_button_style_name",
                        columnNames = {"button_style_id", "name"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DotnetMauiCrossPlatformButtonVisualStateEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(length = 36)
    private String id;

    /**
     * Parent button style that this state extends.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "button_style_id", nullable = false)
    private DotnetMauiCrossPlatformButtonStyleEntity buttonStyle;

    /**
     * Name of the visual state:
     *  - "Normal"
     *  - "Disabled"
     *  - "PointerOver"
     *  - "Pressed"
     */
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    /** Opacity override (0..1), e.g. Disabled → 0.5. */
    @Column(name = "opacity")
    private Double opacity;

    @Column(name = "text_color", length = 128)
    private String textColor;

    @Column(name = "background_color", length = 128)
    private String backgroundColor;

    @Column(name = "border_color", length = 128)
    private String borderColor;

    @OneToOne(mappedBy = "visualSateGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private DotnetMauiCrossPlatformButtonVisualStateShadowEntity shadow;


}
