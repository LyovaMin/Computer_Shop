package by.lyofchik.AppSpring.Service.ProductsService;

import by.lyofchik.AppSpring.Model.DTO.ProductDTO;
import by.lyofchik.AppSpring.Model.Entities.Product;
import by.lyofchik.AppSpring.Repository.ProductRepository;
import by.lyofchik.AppSpring.Service.EntityInterfaces.DeleteEntity;
import by.lyofchik.AppSpring.Service.EntityInterfaces.FindAllEntities;
import by.lyofchik.AppSpring.Service.EntityInterfaces.FindEntity;
import by.lyofchik.AppSpring.Service.EntityInterfaces.SaveEntity;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService implements
        FindAllEntities<Product>,
        DeleteEntity,
        FindEntity<Product>,
        SaveEntity<Product>,
        FindByCategory {

    private final ProductRepository repository;
    private final ModelMapper modelMapper;

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
        List<Product> byCategoryCategoryName = repository.findByCategory_CategoryName(categoryName);
        return byCategoryCategoryName;
    }

    public void delete(Product product) {
        repository.delete(product);
    }

}
