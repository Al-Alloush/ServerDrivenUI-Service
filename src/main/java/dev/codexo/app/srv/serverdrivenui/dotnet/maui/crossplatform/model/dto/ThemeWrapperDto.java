package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThemeWrapperDto {
    private Integer version;
    private LocalDateTime createdDateTime;
    private Map<String, ThemeDto> themes;
    private Map<String, LogosDto> logos;
}
