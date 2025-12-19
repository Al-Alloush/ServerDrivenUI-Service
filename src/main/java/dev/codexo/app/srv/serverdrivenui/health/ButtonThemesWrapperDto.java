package dev.codexo.app.srv.serverdrivenui.health;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ButtonThemesWrapperDto {

    private List<ButtonStylesDto> light = new ArrayList<>();
    private List<ButtonStylesDto> dark = new ArrayList<>();

}
