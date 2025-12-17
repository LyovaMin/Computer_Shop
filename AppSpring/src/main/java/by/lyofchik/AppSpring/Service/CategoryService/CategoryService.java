package by.lyofchik.AppSpring.Service.CategoryService;

import by.lyofchik.AppSpring.Model.Entities.Category;
import by.lyofchik.AppSpring.Repository.CategoryRepository;
import by.lyofchik.AppSpring.Service.EntityInterfaces.FindAllEntities;
import by.lyofchik.AppSpring.Service.EntityInterfaces.FindEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService implements FindAllEntities<Category>{
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> find(int id) {
        return categoryRepository.findById(id);
    }
}
