package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.border;

import dev.codexo.app.srv.serverdrivenui.model.entity.PlatformThemeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "dotnetmaui_crossplatform_border_style",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_maui_border_style_theme_stylekey",
                        columnNames = {"theme_id", "style_key"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorderStyleEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(length = 36)
    private String id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_id", nullable = false)
    private PlatformThemeEntity theme;

    @Column(name = "style_key", nullable = false, length = 100)
    private String styleKey;

    // Background and Stroke
    private String background = "Transparent";
    private String stroke = "Gray";
    private Double strokeThickness = 1.0;

    // Stroke Dash Pattern
    private String strokeDashArray = "0";
    private Double strokeDashOffset = 0.0;

    // Stroke Line Caps and Joins
    @Enumerated(EnumType.STRING)
    private StrokeLineCap strokeLineCap = StrokeLineCap.FLAT;

    @Enumerated(EnumType.STRING)
    private StrokeLineJoin strokeLineJoin = StrokeLineJoin.MITER;

    private Double strokeMiterLimit = 10.0;

    // Corner Radius (StrokeShape)
    private String strokeShape = "RoundRectangle 0";

    // Padding
    private String padding = "0";

    // Size
    private Double heightRequest = -1.0;
    private Double widthRequest = -1.0;
    private Double minimumHeightRequest = -1.0;
    private Double minimumWidthRequest = -1.0;
    private Double maximumHeightRequest = Double.POSITIVE_INFINITY;
    private Double maximumWidthRequest = Double.POSITIVE_INFINITY;

    // Layout
    @Enumerated(EnumType.STRING)
    private LayoutOptions horizontalOptions = LayoutOptions.FILL;

    @Enumerated(EnumType.STRING)
    private LayoutOptions verticalOptions = LayoutOptions.FILL;

    private String margin = "0";

    // Visibility and Interaction
    private Boolean isVisible = true;
    private Boolean isEnabled = true;
    private Double opacity = 1.0;
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
    private Integer zIndex = 0;

    // Flow Direction
    @Enumerated(EnumType.STRING)
    private FlowDirection flowDirection = FlowDirection.MATCH_PARENT;

    // Semantics
    private String automationId = "";

    // Shadow
    @OneToOne(mappedBy = "borderStyle", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private BorderShadowEntity shadow;

    // Visual States
    @OneToMany(mappedBy = "borderStyle", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BorderVisualStateEntity> visualStates = new ArrayList<>();

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;
}
