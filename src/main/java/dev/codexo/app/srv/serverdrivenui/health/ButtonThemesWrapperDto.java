package dev.codexo.app.srv.serverdrivenui.health;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ButtonThemesWrapperDto {

    private List<ButtonStylesDto> light = new ArrayList<>();
    private List<ButtonStylesDto> dark = new ArrayList<>();

}
