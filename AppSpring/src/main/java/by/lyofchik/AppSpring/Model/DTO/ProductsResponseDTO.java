package by.lyofchik.AppSpring.Model.DTO;

import lombok.*;

@Data
@Builder
public class ProductsResponseDTO {
    private String productName;
    private String description;
    private String category;
    private int price;
    private int quantity;
}
