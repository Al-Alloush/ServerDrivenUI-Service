package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.button;

import dev.codexo.app.srv.serverdrivenui.model.entity.PlatformThemeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(
        name = "dotnet_maui_button_style",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_maui_button_style_projectplatform_theme_stylekey",
                        columnNames = {"theme_id", "style_key"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ButtonStyleEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "theme_id", nullable = false)
    private PlatformThemeEntity theme;

    @Column(name = "style_key", nullable = false, length = 100)
    private String styleKey;

    // Appearance properties
    @Embedded
    private Appearance appearance;

    // Typography properties
    @Embedded
    private Typography typography;

    // Layout properties
    @Embedded
    private Layout layout;

    // Border and corners properties
    @Embedded
    private Border border;

    @OneToOne(mappedBy = "buttonStyle", cascade = CascadeType.ALL, orphanRemoval = true)
    private ButtonShadowEntity shadow;

    // Accessibility properties
    @Embedded
    private Accessibility accessibility;


    @Column(name = "image_source")
    private String imageSource;

    @OneToMany(mappedBy = "buttonStyle", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ButtonVisualStateEntity> visualStates;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Appearance {
        @Column(name = "text")
        private String text;

        @Column(name = "text_color", length = 128)
        private String textColor;

        @Column(name = "background_color", length = 128)
        private String backgroundColor;

        @Column(name = "opacity")
        private Double opacity;

        @Column(name = "is_visible")
        private boolean isVisible;

        @Column(name = "is_enabled")
        private boolean isEnabled;
    }

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Typography {

        @Column(name = "font_family", length = 128)
        private String fontFamily;

        @Column(name = "font_size")
        private Double fontSize;

        /**
         * Font attributes like BOLD, ITALIC, etc.
         */
        @Column(name = "font_attributes", length = 32)
        private String FontAttributes;

        @Column(name = "character_spacing")
        private Double characterSpacing;

        /**
         * Line break mode: CharacterWrap, HeadTruncation, MiddleTruncation, NoWrap, TailTruncation, WordWrap.
         */
        @Column(name = "line_break_mode", length = 32)
        private String lineBreakMode;

        /**
         * Text transform: Default, None, Lowercase, Uppercase.
         */
        @Column(name = "text_transform", length = 32)
        private String textTransform;


    }

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Layout {
        @Column(name = "padding", length = 50)
        private String padding;

        @Column(name = "margin", length = 50)
        private String margin;

        @Column(name = "height_request")
        private Double heightRequest;

        @Column(name = "width_request")
        private Double widthRequest;

        @Column(name = "min_height_request")
        private Double minimumHeightRequest;

        @Column(name = "min_width_request")
        private Double minimumWidthRequest;

        /**
         * Horizontal text alignment: Start, Center, End, Fill.
         */
        @Column(name = "horizontal_options", length = 32)
        private String HorizontalOptions;

        @Column(name = "vertical_options", length = 32)
        private String verticalOptions;

        /**
         * example: Left,10
         */
        @Column(name = "content_layout", length = 16)
        private String contentLayout;
    }

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Border {
        @Column(name = "border_color", length = 128)
        private String BorderColor;

        @Column(name = "border_width")
        private Integer borderWidth;

        @Column(name = "corner_radius")
        private Integer cornerRadius;
    }

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Accessibility {
        @Column(name = "semantic_description", length = 512)
        private String semanticDescription;

        @Column(name = "semantic_hint", length = 512)
        private String semanticHint;
    }
}
