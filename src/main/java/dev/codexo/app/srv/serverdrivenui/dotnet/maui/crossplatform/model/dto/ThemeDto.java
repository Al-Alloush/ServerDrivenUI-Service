package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThemeDto {
    private Map<String, String> colors;
    private ComponentsDto components;
}
