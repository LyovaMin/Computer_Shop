package by.lyofchik.AppSpring.Repository;

import by.lyofchik.AppSpring.Model.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
