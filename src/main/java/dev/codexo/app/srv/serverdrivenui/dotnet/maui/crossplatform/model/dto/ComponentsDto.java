package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto;

import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.button.ButtonStyleDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComponentsDto {
    private List<ButtonStyleDto> buttons;
    private List<Object> labels;  // Replace with actual DTO when implemented
    private List<Object> entries; // Replace with actual DTO when implemented
}