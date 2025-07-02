package by.lyofchik.AppSpring.Model.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public class ProductDTO {
    @NotBlank
    private String productName;
    private int price;
}
