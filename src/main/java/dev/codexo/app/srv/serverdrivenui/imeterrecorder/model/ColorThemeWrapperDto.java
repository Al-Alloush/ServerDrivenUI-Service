package dev.codexo.app.srv.serverdrivenui.imeterrecorder.model;


public class ColorThemeWrapperDto {

    private ColorMainPalette light;
    private ColorMainPalette dark;

    public ColorMainPalette getDark() {
        return dark;
    }

    public void setDark(ColorMainPalette dark) {
        this.dark = dark;
    }

    public ColorMainPalette getLight() {
        return light;
    }

    public void setLight(ColorMainPalette light) {
        this.light = light;
    }
}
