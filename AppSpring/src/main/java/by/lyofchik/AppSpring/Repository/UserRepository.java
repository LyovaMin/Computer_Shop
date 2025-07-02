package by.lyofchik.AppSpring.Repository;

import by.lyofchik.AppSpring.Model.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUserName(String username);
    void deleteByUserName(String username);
}
