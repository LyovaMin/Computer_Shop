package by.lyofchik.AppSpring.Mapper;

import by.lyofchik.AppSpring.Model.DTO.ProductDTO;
import by.lyofchik.AppSpring.Model.Entities.Product;

public class ProductMapper {
    public static ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
                .productName(product.getProductName())
                .price(product.getPrice())
                .build();
    }
}
