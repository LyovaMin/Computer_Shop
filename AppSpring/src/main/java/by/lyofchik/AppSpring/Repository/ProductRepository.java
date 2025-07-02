package by.lyofchik.AppSpring.Repository;

import by.lyofchik.AppSpring.Model.Entities.Category;
import by.lyofchik.AppSpring.Model.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByCategory_CategoryName(String categoryName);

    @Modifying
    @Query("DELETE FROM Product g WHERE g.productName = :productName")
    void deleteByName(@Param("productName") String productName);

    Optional<Product> findByProductName(String productName);
}
