package dev.codexo.app.srv.serverdrivenui.imeterrecorder.model;

public class ButtonStylesDto {

    private String key;
    private String background;
    private String textColor;
    private int cornerRadius;

    public ButtonStylesDto(String key, String background, String textColor, int cornerRadius) {
        this.key = key;
        this.background = background;
        this.textColor = textColor;
        this.cornerRadius = cornerRadius;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
    }
}
