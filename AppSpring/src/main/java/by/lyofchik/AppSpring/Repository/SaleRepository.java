package by.lyofchik.AppSpring.Repository;

import by.lyofchik.AppSpring.Model.Entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Integer> {
    List<Sale> findByUserUserName(String username);

}
