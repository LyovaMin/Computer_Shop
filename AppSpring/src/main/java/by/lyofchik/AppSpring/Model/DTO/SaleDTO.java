package by.lyofchik.AppSpring.Model.DTO;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public class SaleDTO {
    String productName;
    Integer productPrice;
    String userName;
    LocalDate date;
}
