package by.lyofchik.AppSpring.Model.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductDTO {
    @NotBlank
    private String productName;
    private int price;
}
