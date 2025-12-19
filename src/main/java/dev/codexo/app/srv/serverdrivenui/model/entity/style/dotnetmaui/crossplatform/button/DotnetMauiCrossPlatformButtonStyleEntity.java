package dev.codexo.app.srv.serverdrivenui.model.entity.style.dotnetmaui.crossplatform.button;

import dev.codexo.app.srv.serverdrivenui.model.entity.ProjectPlatformEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Cross-platform .NET MAUI Button style definition.
 * <p>
 * Represents the "base" XAML style, e.g.:
 * <p>
 * <Style TargetType="Button" x:Key="RichButton">
 *   <!-- base Setters -->
 * </Style>
 * <p>
 * More complex pieces are normalized:
 *  - Shadow  → {@link DotnetMauiCrossPlatformButtonShadowEntity}
 *  - Visual states → {@link DotnetMauiCrossPlatformButtonVisualStateEntity}
 *
 * Colors and font families are expressed via tokens wherever possible
 * (PRIMARY, SECONDARY, BRAND_FONT, CUSTOM, ...) so they can inherit
 * from BrandIdentity without duplicating raw values.
 */
@Entity
@Table(
        name = "dotnet_maui_button_style",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_maui_button_style_projectplatform_stylekey",
                        columnNames = {"project_platform_id", "style_key"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DotnetMauiCrossPlatformButtonStyleEntity {

    // ---------------------------------------------------------------------
    // Identity & relations
    // ---------------------------------------------------------------------

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(length = 36)
    private String id;

    /**
     * Project + platform this style belongs to
     * (e.g. "iMeterRecorder" + ".NET MAUI").
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_platform_id", nullable = false)
    private ProjectPlatformEntity projectPlatform;

    /**
     * Style key used by the client, e.g. "RichButton", "PrimaryButton".
     * Must be unique per ProjectPlatform.
     */
    @Column(name = "style_key", nullable = false, length = 100)
    private String styleKey;

    // ---------------------------------------------------------------------
    // Appearance: text, colors, visibility
    // ---------------------------------------------------------------------

    /**
     * Default button text.
     * XAML: <Setter Property="Text" Value="Click me" />.
     */
    @Column(name = "default_text", length = 255)
    private String defaultText;

    /**
     * LIGHT theme text color token
     * (WHITE, BRAND_TEXT, PRIMARY, CUSTOM, ...).
     */
    @Column(name = "text_color_light_token", length = 32)
    private String textColorLightToken;

    /**
     * Custom LIGHT theme text color (hex) when token == "CUSTOM".
     * Example: "#FFFFFF".
     */
    @Column(name = "text_color_light_custom", length = 16)
    private String textColorLightCustom;

    /**
     * DARK theme text color token.
     */
    @Column(name = "text_color_dark_token", length = 32)
    private String textColorDarkToken;

    /**
     * Custom DARK theme text color (hex) when token == "CUSTOM".
     */
    @Column(name = "text_color_dark_custom", length = 16)
    private String textColorDarkCustom;

    /**
     * LIGHT theme background color token
     * (PRIMARY, SECONDARY, TERTIARY, CUSTOM, ...).
     */
    @Column(name = "background_color_light_token", length = 32)
    private String backgroundColorLightToken;

    /**
     * Custom LIGHT background color when token == "CUSTOM".
     */
    @Column(name = "background_color_light_custom", length = 16)
    private String backgroundColorLightCustom;

    /**
     * DARK theme background color token.
     */
    @Column(name = "background_color_dark_token", length = 32)
    private String backgroundColorDarkToken;

    /**
     * Custom DARK background color when token == "CUSTOM".
     */
    @Column(name = "background_color_dark_custom", length = 16)
    private String backgroundColorDarkCustom;

    /**
     * Base opacity (0..1).
     * XAML: <Setter Property="Opacity" Value="1" />.
     */
    @Column(name = "opacity")
    private Double opacity;

    /**
     * Initial visibility.
     * XAML: <Setter Property="IsVisible" Value="True" />.
     */
    @Column(name = "is_visible")
    private Boolean visible;

    /**
     * Initial enabled state.
     * XAML: <Setter Property="IsEnabled" Value="True" />.
     */
    @Column(name = "is_enabled")
    private Boolean enabled;

    // ---------------------------------------------------------------------
    // Typography
    // ---------------------------------------------------------------------

    /**
     * Font family strategy:
     *  - BRAND_FONT → use BrandIdentity.fontFamily
     *  - CUSTOM     → use fontFamilyCustom
     *  - SYSTEM     → platform default
     */
    @Column(name = "font_family_token", length = 32)
    private String fontFamilyToken;

    /**
     * Custom font family name when token == "CUSTOM".
     * XAML: "OpenSansRegular".
     */
    @Column(name = "font_family_custom", length = 255)
    private String fontFamilyCustom;

    /**
     * Font size in device-independent units.
     * XAML: <Setter Property="FontSize" Value="16" />.
     */
    @Column(name = "font_size")
    private Double fontSize;

    /**
     * Font attributes ("None", "Bold", "Italic", "BoldItalic").
     * XAML: <Setter Property="FontAttributes" Value="Bold" />.
     */
    @Column(name = "font_attributes", length = 32)
    private String fontAttributes;

    /**
     * Additional spacing between characters.
     * XAML: <Setter Property="CharacterSpacing" Value="0" />.
     */
    @Column(name = "character_spacing")
    private Double characterSpacing;

    /**
     * Line break behaviour ("NoWrap", "WordWrap", ...).
     * XAML: <Setter Property="LineBreakMode" Value="NoWrap" />.
     */
    @Column(name = "line_break_mode", length = 32)
    private String lineBreakMode;

    /**
     * Text transform ("None", "Uppercase", "Lowercase").
     * XAML: <Setter Property="TextTransform" Value="None" />.
     */
    @Column(name = "text_transform", length = 32)
    private String textTransform;

    // ---------------------------------------------------------------------
    // Layout: size, padding, margin, alignment
    // ---------------------------------------------------------------------

    /**
     * Padding inside the button (left,top,right,bottom or h,v).
     * XAML: <Setter Property="Padding" Value="16,10" />.
     */
    @Column(name = "padding", length = 50)
    private String padding;

    /**
     * External margin of the button.
     * XAML: <Setter Property="Margin" Value="0" />.
     */
    @Column(name = "margin", length = 50)
    private String margin;

    /**
     * Requested height; -1 = "auto".
     * XAML: <Setter Property="HeightRequest" Value="-1" />.
     */
    @Column(name = "height_request")
    private Double heightRequest;

    /**
     * Requested width; -1 = "auto".
     * XAML: <Setter Property="WidthRequest" Value="-1" />.
     */
    @Column(name = "width_request")
    private Double widthRequest;

    /**
     * Minimum height (e.g. 44).
     * XAML: <Setter Property="MinimumHeightRequest" Value="44" />.
     */
    @Column(name = "min_height_request")
    private Double minimumHeightRequest;

    /**
     * Minimum width (e.g. 44).
     * XAML: <Setter Property="MinimumWidthRequest" Value="44" />.
     */
    @Column(name = "min_width_request")
    private Double minimumWidthRequest;

    /**
     * Horizontal layout options ("Start", "Center", "End", "Fill").
     * XAML: <Setter Property="HorizontalOptions" Value="Fill" />.
     */
    @Column(name = "horizontal_options", length = 32)
    private String horizontalOptions;

    /**
     * Vertical layout options ("Start", "Center", "End", "Fill").
     * XAML: <Setter Property="VerticalOptions" Value="Center" />.
     */
    @Column(name = "vertical_options", length = 32)
    private String verticalOptions;

    /**
     * ContentLayout image position ("Left", "Right", "Top", "Bottom").
     * XAML: ContentLayout="Left,10".
     */
    @Column(name = "content_layout_position", length = 16)
    private String contentLayoutPosition;

    /**
     * Spacing between icon and text in ContentLayout.
     */
    @Column(name = "content_layout_spacing")
    private Double contentLayoutSpacing;

    // ---------------------------------------------------------------------
    // Border & corners
    // ---------------------------------------------------------------------

    /**
     * LIGHT theme border color token.
     * XAML: BorderColor Light binding.
     */
    @Column(name = "border_color_light_token", length = 32)
    private String borderColorLightToken;

    /** Custom LIGHT border color when token == "CUSTOM". */
    @Column(name = "border_color_light_custom", length = 16)
    private String borderColorLightCustom;

    /**
     * DARK theme border color token.
     */
    @Column(name = "border_color_dark_token", length = 32)
    private String borderColorDarkToken;

    /** Custom DARK border color when token == "CUSTOM". */
    @Column(name = "border_color_dark_custom", length = 16)
    private String borderColorDarkCustom;

    /**
     * Border width in device-independent units.
     * XAML: <Setter Property="BorderWidth" Value="1" />.
     */
    @Column(name = "border_width")
    private Double borderWidth;

    /**
     * Corner radius to round button edges.
     * XAML: <Setter Property="CornerRadius" Value="8" />.
     */
    @Column(name = "corner_radius")
    private Integer cornerRadius;

    // ---------------------------------------------------------------------
    // Shadow, image, accessibility & visual states
    // ---------------------------------------------------------------------

    /**
     * Base shadow configuration (normal state).
     * XAML: <Setter Property="Shadow"> <Shadow ... /> </Setter>.
     */
    @OneToOne(mappedBy = "buttonStyle",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private DotnetMauiCrossPlatformButtonShadowEntity shadow;

    /**
     * Icon shown inside the button.
     * XAML: <Setter Property="ImageSource" Value="icon_save.png" />.
     */
    @Column(name = "image_source", length = 255)
    private String imageSource;

    /**
     * Accessibility description for screen readers.
     * XAML: SemanticProperties.Description.
     */
    @Column(name = "semantic_description", length = 512)
    private String semanticDescription;

    /**
     * Accessibility hint, e.g. "Double tap to save".
     * XAML: SemanticProperties.Hint.
     */
    @Column(name = "semantic_hint", length = 512)
    private String semanticHint;

    /**
     * Visual states (Normal, Disabled, PointerOver, Pressed, ...).
     * Each state can override opacity, colors, border, scale and shadow.
     */
    @OneToMany(mappedBy = "buttonStyle",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<DotnetMauiCrossPlatformButtonVisualStateEntity> visualStates;

    // ---------------------------------------------------------------------

    /**
     * Creation timestamp for auditing.
     */
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;
}
