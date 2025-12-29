package dev.codexo.app.srv.serverdrivenui.model.entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

/**
 * Target platform for which styles can be configured.
 * Examples:
 *   - code = "DOTNET_MAUI", name = ".NET MAUI"
 *   - code = "REACT_WEB",   name = "React Web"
 */
@Entity
@Table(name = "platform")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlatformEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(length = 36)
    private String id;

    @Column(nullable = false, unique = true, length = 50)
    private String code;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @OneToMany(mappedBy = "platform", fetch = FetchType.LAZY)
    private List<ProjectPlatformEntity> projectPlatforms;
}
