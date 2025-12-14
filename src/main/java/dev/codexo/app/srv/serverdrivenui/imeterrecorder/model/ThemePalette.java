package dev.codexo.app.srv.serverdrivenui.imeterrecorder.model;


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

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public String getPrimaryDark() {
        return primaryDark;
    }

    public void setPrimaryDark(String primaryDark) {
        this.primaryDark = primaryDark;
    }

    public String getPrimaryDarkText() {
        return primaryDarkText;
    }

    public void setPrimaryDarkText(String primaryDarkText) {
        this.primaryDarkText = primaryDarkText;
    }

    public String getSecondary() {
        return secondary;
    }

    public void setSecondary(String secondary) {
        this.secondary = secondary;
    }

    public String getSecondaryDark() {
        return secondaryDark;
    }

    public void setSecondaryDark(String secondaryDark) {
        this.secondaryDark = secondaryDark;
    }

    public String getSecondaryDarkText() {
        return secondaryDarkText;
    }

    public void setSecondaryDarkText(String secondaryDarkText) {
        this.secondaryDarkText = secondaryDarkText;
    }

    public String getTertiary() {
        return tertiary;
    }

    public void setTertiary(String tertiary) {
        this.tertiary = tertiary;
    }
}
