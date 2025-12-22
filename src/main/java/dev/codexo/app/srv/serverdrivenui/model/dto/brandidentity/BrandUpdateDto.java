package dev.codexo.app.srv.serverdrivenui.model.dto.brandidentity;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandUpdateDto {
    private String brandName;
    private String primaryColor;
    private String secondaryColor;
    private String tertiaryColor;
    private String fontFamily;
    private String logoUrl;
    private String description;
}
