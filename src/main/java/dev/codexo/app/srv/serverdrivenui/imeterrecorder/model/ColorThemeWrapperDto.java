package dev.codexo.app.srv.serverdrivenui.imeterrecorder.model;


public class ColorThemeWrapperDto {

    private ThemePalette light;
    private ThemePalette dark;

    public ThemePalette getDark() {
        return dark;
    }

    public void setDark(ThemePalette dark) {
        this.dark = dark;
    }

    public ThemePalette getLight() {
        return light;
    }

    public void setLight(ThemePalette light) {
        this.light = light;
    }
}
