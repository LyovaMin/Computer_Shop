package by.lyofchik.AppSpring.Service.ProductsService;

import by.lyofchik.AppSpring.Filter.ProductFilter;
import by.lyofchik.AppSpring.Mapper.ProductMapper;
import by.lyofchik.AppSpring.Model.DTO.ProductDTO;
import by.lyofchik.AppSpring.Model.DTO.QPredicate;
import by.lyofchik.AppSpring.Model.Entities.Product;
import by.lyofchik.AppSpring.Repository.ProductRepository;
import by.lyofchik.AppSpring.Service.EntityInterfaces.*;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

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
    private final ProductMapper mapper;


    @Override
    @CachePut("products")
    public boolean delete(String name) {
        try {
            repository.deleteByName(name);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    @CachePut("products")
    public Product save(Product entity) {
        return repository.save(entity);
    }

    @Cacheable("products")
    public Page<Product> findAll(ProductFilter filter, Pageable pageable) {
        var predicates = QPredicate.builder()
                .add(filter.productName(), product.productName::containsIgnoreCase)
                .add(filter.price(), product.price::lt)
                .add(filter.categoryName(), product.category.categoryName::containsIgnoreCase)
                .build();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return repository.findAll(predicates, pageable);
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

    @CachePut("products")
    public void delete(Product product) {
        repository.delete(product);
    }

    public Product findById(int id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public List<Product> findAllByFilter(ProductFilter filter) {
        return List.of();
    }
}
