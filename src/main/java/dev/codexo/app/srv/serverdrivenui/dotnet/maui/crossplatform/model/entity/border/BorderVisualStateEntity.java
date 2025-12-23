package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.border;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "dotnet_maui_border_visual_state",
        uniqueConstraints = @UniqueConstraint(name = "uk_maui_border_state_border_style_name",
                columnNames = {"border_style_id", "name"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BorderVisualStateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "border_style_id", nullable = false)
    private BorderStyleEntity borderStyle;

    private String name; // e.g., "Normal", "Disabled"

    private Double opacity;
}
