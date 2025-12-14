package dev.codexo.app.srv.serverdrivenui.imeterrecorder.model;

import java.util.ArrayList;
import java.util.List;

public class ButtonThemesWrapperDto {

    private List<ButtonStylesDto> light = new ArrayList<>();
    private List<ButtonStylesDto> dark = new ArrayList<>();

    public List<ButtonStylesDto> getLight() {
        return light;
    }

    public void setLight(List<ButtonStylesDto> light) {
        this.light = light;
    }

    public List<ButtonStylesDto> getDark() {
        return dark;
    }

    public void setDark(List<ButtonStylesDto> dark) {
        this.dark = dark;
    }
}
