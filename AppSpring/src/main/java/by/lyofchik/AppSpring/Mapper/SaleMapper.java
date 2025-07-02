package by.lyofchik.AppSpring.Mapper;

import by.lyofchik.AppSpring.Model.DTO.SaleDTO;
import by.lyofchik.AppSpring.Model.Entities.Sale;

public class SaleMapper {
    public static SaleDTO toDTO(Sale sale) {
        return SaleDTO.builder()
                .productName(sale.getUser().getUserName())
                .productPrice(sale.getProduct().getPrice())
                .productName(sale.getProduct().getProductName())
                .date(sale.getSaleDate())
                .build();
    }
}
