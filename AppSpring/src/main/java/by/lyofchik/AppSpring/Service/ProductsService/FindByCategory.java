package by.lyofchik.AppSpring.Service.ProductsService;

import by.lyofchik.AppSpring.Model.Entities.Product;

import java.util.List;

public interface FindByCategory {
    List<Product> findByCategory_CategoryName(String categoryName);
}
