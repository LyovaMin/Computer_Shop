package by.lyofchik.AppSpring.Service.SaleService;

import by.lyofchik.AppSpring.Model.Entities.Sale;

import java.util.List;

public interface FindUserSales {
    List<Sale> findByShopUserUserName(String username);
}
