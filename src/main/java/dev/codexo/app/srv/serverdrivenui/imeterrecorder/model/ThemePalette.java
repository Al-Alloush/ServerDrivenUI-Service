package dev.codexo.app.srv.serverdrivenui.imeterrecorder.model;

import lombok.Data;

@Data
public class ThemePalette {

    private String primary;
    private String primaryColor;
    private String primaryDark;
    private String primaryDarkText;
    private String secondary;
    private String secondaryDark;
    private String secondaryDarkText;
    private String tertiary;

    public ThemePalette(String primary, String primaryDark, String primaryDarkText,
                        String secondary, String secondaryDark, String secondaryDarkText,
                        String tertiary) {
        this.primary = primary;
        this.primaryColor = primary;
        this.primaryDark = primaryDark;
        this.primaryDarkText = primaryDarkText;
        this.secondary = secondary;
        this.secondaryDark = secondaryDark;
        this.secondaryDarkText = secondaryDarkText;
        this.tertiary = tertiary;
    }
}
