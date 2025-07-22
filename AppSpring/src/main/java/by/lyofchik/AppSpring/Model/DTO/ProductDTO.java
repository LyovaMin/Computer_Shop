package by.lyofchik.AppSpring.Model.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class ProductDTO implements Serializable {
    @NotBlank
    private String productName;
    private Integer price;
}
