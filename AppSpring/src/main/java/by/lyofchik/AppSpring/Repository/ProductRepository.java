package by.lyofchik.AppSpring.Repository;

import by.lyofchik.AppSpring.Model.Entities.Product;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer>,
        QuerydslPredicateExecutor<Product> {
    List<Product> findByCategory_CategoryName(String categoryName);

    @Modifying
    @Query("DELETE FROM Product g WHERE g.productName = :productName")
    void deleteByName(@Param("productName") String productName);

    Optional<Product> findByProductName(String productName);
}
