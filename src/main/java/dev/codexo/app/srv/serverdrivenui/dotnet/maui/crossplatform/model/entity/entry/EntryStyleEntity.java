package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.entry;

import dev.codexo.app.srv.serverdrivenui.model.entity.PlatformThemeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "dotnetmaui_crossplatform_entry_style",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_maui_entry_style_theme_stylekey",
                        columnNames = {"theme_id", "style_key"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntryStyleEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "theme_id", nullable = false)
    private PlatformThemeEntity theme;

    @Column(name = "style_key", nullable = false, length = 100)
    private String styleKey; // e.g., "default_entry_style", "digit_entry", "password_entry"

    // Colors
    @Column(name = "background_color", length = 128)
    private String backgroundColor = "#FFFFFF";

    @Column(name = "text_color", length = 128)
    private String textColor = "#1A1A1A";

    @Column(name = "placeholder_color", length = 128)
    private String placeholderColor = "#9E9E9E";

    @Column(name = "cursor_color", length = 128)
    private String cursorColor = "#007AFF";

    @Column(name = "selection_highlight_color", length = 128)
    private String selectionHighlightColor = "#B3D7FF";

    // Font Properties
    @Column(name = "font_size")
    private Double fontSize = 16.0;

    @Column(name = "font_family", length = 128)
    private String fontFamily = "OpenSansRegular";

    // Size and Layout
    @Column(name = "height_request")
    private Double heightRequest = 48.0;

    @Column(name = "margin", length = 50)
    private String margin = "0";

    // Entry Behavior
    @Enumerated(EnumType.STRING)
    @Column(name = "clear_button_visibility")
    private ClearButtonVisibility clearButtonVisibility = ClearButtonVisibility.WHILE_EDITING;

    @Enumerated(EnumType.STRING)
    @Column(name = "return_type")
    private ReturnType returnType = ReturnType.NEXT;

    // Text Alignment
    @Enumerated(EnumType.STRING)
    @Column(name = "horizontal_text_alignment")
    private TextAlignment horizontalTextAlignment = TextAlignment.START;

    @Enumerated(EnumType.STRING)
    @Column(name = "vertical_text_alignment")
    private TextAlignment verticalTextAlignment = TextAlignment.CENTER;

    // Additional Properties for EntryDigit and EntryPassword
    @Enumerated(EnumType.STRING)
    @Column(name = "keyboard")
    private Keyboard keyboard;

    @Column(name = "is_password")
    private Boolean isPassword = false;

    @Column(name = "max_length")
    private Integer maxLength;

    // Shadow
    @OneToOne(mappedBy = "entryStyle", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private EntryShadowEntity shadow;

    // Visual States
    @OneToMany(mappedBy = "entryStyle", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<EntryVisualStateEntity> visualStates = new ArrayList<>();

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    // Nested Enums
    public enum ClearButtonVisibility {
        NEVER,
        WHILE_EDITING
    }

    public enum ReturnType {
        DEFAULT,
        DONE,
        GO,
        NEXT,
        SEARCH,
        SEND
    }

    public enum TextAlignment {
        START,
        CENTER,
        END
    }

    public enum Keyboard {
        DEFAULT,
        TEXT,
        CHAT,
        URL,
        EMAIL,
        TELEPHONE,
        NUMERIC
    }
}