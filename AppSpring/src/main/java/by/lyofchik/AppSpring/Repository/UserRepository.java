package by.lyofchik.AppSpring.Repository;

import by.lyofchik.AppSpring.Model.Entities.Sale;
import by.lyofchik.AppSpring.Model.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUserName(String username);
    void deleteByUserName(String username);

    @Modifying
    @Query("SELECT s FROM Sale s WHERE s.user.userName = :username")
    List<Sale> findAllSalesByUserName(String username);
}
