package dev.codexo.app.srv.serverdrivenui.model.entity.style.dotnetmaui.crossplatform.button;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;

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
                        name = "uk_maui_button_state_button_style_state_name",
                        columnNames = {"button_style_id", "state_name"}
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
    @Column(name = "state_name", nullable = false, length = 50)
    private String stateName;

    // --------------------------- overrides ---------------------------- //

    /** Opacity override (0..1), e.g. Disabled → 0.5. */
    @Column(name = "opacity")
    private Double opacity;

    // Background colors (Light/Dark)

    @Column(name = "background_color_light_token", length = 32)
    private String backgroundColorLightToken;

    @Column(name = "background_color_light_custom", length = 16)
    private String backgroundColorLightCustom;

    @Column(name = "background_color_dark_token", length = 32)
    private String backgroundColorDarkToken;

    @Column(name = "background_color_dark_custom", length = 16)
    private String backgroundColorDarkCustom;

    // Text colors (Light/Dark)

    @Column(name = "text_color_light_token", length = 32)
    private String textColorLightToken;

    @Column(name = "text_color_light_custom", length = 16)
    private String textColorLightCustom;

    @Column(name = "text_color_dark_token", length = 32)
    private String textColorDarkToken;

    @Column(name = "text_color_dark_custom", length = 16)
    private String textColorDarkCustom;

    // Border colors (Light/Dark)

    @Column(name = "border_color_light_token", length = 32)
    private String borderColorLightToken;

    @Column(name = "border_color_light_custom", length = 16)
    private String borderColorLightCustom;

    @Column(name = "border_color_dark_token", length = 32)
    private String borderColorDarkToken;

    @Column(name = "border_color_dark_custom", length = 16)
    private String borderColorDarkCustom;

    /** Optional border width override. */
    @Column(name = "border_width")
    private Double borderWidth;

    /** Optional corner radius override. */
    @Column(name = "corner_radius")
    private Integer cornerRadius;

    /**
     * Scale override for this state (e.g. 0.97 for Pressed).
     */
    @Column(name = "scale")
    private Double scale;

    // Simple shadow overrides

    @Column(name = "shadow_opacity")
    private Double shadowOpacity;

    @Column(name = "shadow_radius")
    private Double shadowRadius;

    @Column(name = "shadow_offset_x")
    private Double shadowOffsetX;

    @Column(name = "shadow_offset_y")
    private Double shadowOffsetY;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;
}
