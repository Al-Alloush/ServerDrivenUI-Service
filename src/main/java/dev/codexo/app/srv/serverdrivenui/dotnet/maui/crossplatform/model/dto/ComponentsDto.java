package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto;

import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.border.BorderStyleDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.button.ButtonStyleDto;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.dto.label.LabelStyleDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComponentsDto {
    private List<ButtonStyleDto> buttons;
    private List<LabelStyleDto> labels;
    private List<Object> entries; // Replace with actual DTO when implemented
    private List<BorderStyleDto> borders; // Border components
}