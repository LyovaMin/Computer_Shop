package by.lyofchik.AppSpring.Mapper;

import by.lyofchik.AppSpring.Configuration.MapperConfig;
import by.lyofchik.AppSpring.Model.DTO.ProductDTO;
import by.lyofchik.AppSpring.Model.Entities.Product;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    private static ModelMapper mapper;

    public static ProductDTO toDTO(Product product) {
        return mapper.map(product, ProductDTO.class);
    }
}
