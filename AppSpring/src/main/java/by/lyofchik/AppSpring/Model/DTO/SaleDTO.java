package by.lyofchik.AppSpring.Model.DTO;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class SaleDTO {
    String productName;
    Integer productPrice;
    String userName;
    LocalDate date;
}
