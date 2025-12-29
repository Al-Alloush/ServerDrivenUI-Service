package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.label;

import dev.codexo.app.srv.serverdrivenui.model.entity.PlatformThemeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Platform-specific label/text style for .NET MAUI.
 */
@Entity
@Table(
        name = "dotnetmaui_crossplatform_label_style",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_maui_label_style_theme_stylekey",
                        columnNames = {"theme_id", "style_key"}
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
    @JoinColumn(name = "theme_id", nullable = false)
    private PlatformThemeEntity theme;

    @Column(name = "style_key", nullable = false, length = 100)
    private String styleKey; // e.g. "default_label_style"

    // Text Content
    @Column(name = "text")
    private String text = "";

    @Column(name = "text_color", length = 128)
    private String textColor = "{AppThemeBinding Light={StaticResource Black}, Dark={StaticResource White}}";

    @Column(name = "formatted_text")
    private String formattedText; // null by default

    // Font Properties
    @Column(name = "font_family", length = 128)
    private String fontFamily = "OpenSansRegular";

    @Column(name = "font_size")
    private Double fontSize = 14.0;

    @Enumerated(EnumType.STRING)
    @Column(name = "font_attributes")
    private FontAttributes fontAttributes = FontAttributes.NONE;

    @Column(name = "font_auto_scaling_enabled")
    private Boolean fontAutoScalingEnabled = true;

    // Text Layout
    @Enumerated(EnumType.STRING)
    @Column(name = "text_transform")
    private TextTransform textTransform = TextTransform.NONE;

    @Column(name = "character_spacing")
    private Double characterSpacing = 0.0;

    @Enumerated(EnumType.STRING)
    @Column(name = "line_break_mode")
    private LineBreakMode lineBreakMode = LineBreakMode.TAIL_TRUNCATION;

    @Column(name = "max_lines")
    private Integer maxLines = -1;

    @Column(name = "line_height")
    private Double lineHeight = -1.0;

    // Text Alignment
    @Enumerated(EnumType.STRING)
    @Column(name = "horizontal_text_alignment")
    private TextAlignment horizontalTextAlignment = TextAlignment.START;

    @Enumerated(EnumType.STRING)
    @Column(name = "vertical_text_alignment")
    private TextAlignment verticalTextAlignment = TextAlignment.CENTER;

    // Text Decoration
    @Enumerated(EnumType.STRING)
    @Column(name = "text_decorations")
    private TextDecorations textDecorations = TextDecorations.NONE;

    // Padding
    @Column(name = "padding", length = 50)
    private String padding = "0";

    // Size
    @Column(name = "height_request")
    private Double heightRequest = -1.0;

    @Column(name = "width_request")
    private Double widthRequest = -1.0;

    @Column(name = "minimum_height_request")
    private Double minimumHeightRequest = -1.0;

    @Column(name = "minimum_width_request")
    private Double minimumWidthRequest = -1.0;

    @Column(name = "maximum_height_request")
    private Double maximumHeightRequest = Double.POSITIVE_INFINITY;

    @Column(name = "maximum_width_request")
    private Double maximumWidthRequest = Double.POSITIVE_INFINITY;

    // Layout
    @Enumerated(EnumType.STRING)
    @Column(name = "horizontal_options")
    private LayoutOptions horizontalOptions = LayoutOptions.START;

    @Enumerated(EnumType.STRING)
    @Column(name = "vertical_options")
    private LayoutOptions verticalOptions = LayoutOptions.CENTER;

    @Column(name = "margin", length = 50)
    private String margin = "0";

    // Background
    @Column(name = "background_color", length = 128)
    private String backgroundColor = "Transparent";

    // Visibility and Interaction
    @Column(name = "is_visible")
    private Boolean isVisible = true;

    @Column(name = "is_enabled")
    private Boolean isEnabled = true;

    @Column(name = "opacity")
    private Double opacity = 1.0;

    @Column(name = "input_transparent")
    private Boolean inputTransparent = false;

    // Transforms
    @Column(name = "anchor_x")
    private Double anchorX = 0.5;

    @Column(name = "anchor_y")
    private Double anchorY = 0.5;

    @Column(name = "rotation")
    private Double rotation = 0.0;

    @Column(name = "rotation_x")
    private Double rotationX = 0.0;

    @Column(name = "rotation_y")
    private Double rotationY = 0.0;

    @Column(name = "scale")
    private Double scale = 1.0;

    @Column(name = "scale_x")
    private Double scaleX = 1.0;

    @Column(name = "scale_y")
    private Double scaleY = 1.0;

    @Column(name = "translation_x")
    private Double translationX = 0.0;

    @Column(name = "translation_y")
    private Double translationY = 0.0;

    // Z-Index
    @Column(name = "z_index")
    private Integer zIndex = 0;

    // Flow Direction
    @Enumerated(EnumType.STRING)
    @Column(name = "flow_direction")
    private FlowDirection flowDirection = FlowDirection.MATCH_PARENT;

    // Semantics
    @Column(name = "automation_id")
    private String automationId = "";

    // Shadow
    @OneToOne(mappedBy = "labelStyle", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private LabelShadowEntity shadow;

    // Visual States
    @OneToMany(mappedBy = "labelStyle", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<LabelVisualStateEntity> visualStates = new ArrayList<>();

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;
}
