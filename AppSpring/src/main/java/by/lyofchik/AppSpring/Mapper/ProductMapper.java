package by.lyofchik.AppSpring.Mapper;

import by.lyofchik.AppSpring.Configuration.MapperConfig;
import by.lyofchik.AppSpring.Model.DTO.ProductDTO;
import by.lyofchik.AppSpring.Model.Entities.Product;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductMapper {
    private ModelMapper mapper;

    public ProductDTO toDTO(Product product) {
        return mapper.map(product, ProductDTO.class);
    }
}
