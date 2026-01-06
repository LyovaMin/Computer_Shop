package by.lyofchik.AppSpring.Mapper;

import by.lyofchik.AppSpring.Model.DTO.SaleDTO;
import by.lyofchik.AppSpring.Model.Entities.Sale;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SaleMapper {
    private final ModelMapper mapper;

    public SaleDTO toDTO(Sale sale) {
        return mapper.map(sale, SaleDTO.class);
    }
}
