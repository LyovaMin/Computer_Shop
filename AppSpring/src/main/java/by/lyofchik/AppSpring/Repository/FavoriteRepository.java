package by.lyofchik.AppSpring.Repository;

import by.lyofchik.AppSpring.Model.Entities.Favorite;
import by.lyofchik.AppSpring.Model.Entities.Product;
import by.lyofchik.AppSpring.Model.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    List<Favorite> findByUser(User user);
}
