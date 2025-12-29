package dev.codexo.app.srv.serverdrivenui.health;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}
