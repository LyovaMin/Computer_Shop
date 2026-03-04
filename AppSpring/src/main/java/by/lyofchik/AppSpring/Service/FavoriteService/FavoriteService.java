package by.lyofchik.AppSpring.Service.FavoriteService;

import by.lyofchik.AppSpring.Model.Entities.*;
import by.lyofchik.AppSpring.Repository.FavoriteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FavoriteService {
    FavoriteRepository repository;

    public Favorite save(Product product, User user) {
        return repository.save(Favorite.builder()
                .user(user)
                .product(product)
                .build());
    }

    public List<Favorite> findAll(User user) {
        return repository.findByUser(user);
    }
}
