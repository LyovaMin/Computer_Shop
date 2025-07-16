package by.lyofchik.AppSpring.Service.ProductsService;

import by.lyofchik.AppSpring.Filter.ProductFilter;
import by.lyofchik.AppSpring.Mapper.ProductMapper;
import by.lyofchik.AppSpring.Model.DTO.ProductDTO;
import by.lyofchik.AppSpring.Model.DTO.QPredicate;
import by.lyofchik.AppSpring.Model.Entities.Product;
import by.lyofchik.AppSpring.Repository.ProductRepository;
import by.lyofchik.AppSpring.Service.EntityInterfaces.*;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static by.lyofchik.AppSpring.Model.QEntities.QProduct.product;

@Service
@AllArgsConstructor
public class ProductService implements
        FindAllEntities<Product>,
        DeleteEntity,
        FindEntity<Product>,
        SaveEntity<Product>,
        FindByCategory,
        FindAllEntitiesByFilter<Product> {

    private final ProductRepository repository;
    private final EntityManager entityManager;


    @Override
    public boolean delete(String name) {
        try {
            repository.deleteByName(name);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Product save(Product entity) {
        return repository.save(entity);
    }

    @Override
    public List<Product> findAllByFilter(ProductFilter filter) {
        var predicates = QPredicate.builder()
                .add(filter.productName(), product.productName::containsIgnoreCase)
                .add(filter.price(), product.price::lt)
                .add(filter.categoryName(), product.category.categoryName::containsIgnoreCase)
                .build();

        return new JPAQuery<Product>(entityManager)
                .select(product)
                .from(product)
                .where(predicates)
                .fetch();
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
//        return repository.findAll().stream()
//                .map(entity -> modelMapper.map(entity, ProductDTO.class))
//                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> find(String name) {
        return repository.findByProductName(name);
    }

    @Override
    public List<Product> findByCategory_CategoryName(String categoryName) {
        return repository.findByCategory_CategoryName(categoryName);
    }

    public void delete(Product product) {
        repository.delete(product);
    }

    public Product findById(int id) {
        return repository.findById(id).orElse(null);
    }
}
