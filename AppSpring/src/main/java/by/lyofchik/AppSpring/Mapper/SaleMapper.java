package by.lyofchik.AppSpring.Mapper;

import by.lyofchik.AppSpring.Model.DTO.SaleDTO;
import by.lyofchik.AppSpring.Model.Entities.Sale;
import org.modelmapper.ModelMapper;

public class SaleMapper {
    private static ModelMapper mapper;

    public static SaleDTO toDTO(Sale sale) {
        return mapper.map(sale, SaleDTO.class);
    }
}
