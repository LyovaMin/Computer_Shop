package by.lyofchik.AppSpring.Controller.Rest;

import by.lyofchik.AppSpring.Mapper.ProductMapper;
import by.lyofchik.AppSpring.Model.DTO.ProductDTO;
import by.lyofchik.AppSpring.Model.Entities.Product;
import by.lyofchik.AppSpring.Service.ProductsService.ProductService;
import lombok.AllArgsConstructor;
//import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductRestController {
    ProductService productService;

    @GetMapping("/DTO")
    public List<ProductDTO> getProducts() {
        return productService.findAll().stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{name}")
    public Optional<Product> findByName(@PathVariable String name) {
        return productService.find(name);
    }

    @GetMapping("/category/{categoryName}")
    public List<Product> findByCategory(@PathVariable String categoryName) {
        return productService.findByCategory_CategoryName(categoryName);
    }

    @PostMapping("save_product")
    public Product save(@RequestBody Product product) {

        return productService.save(product);
    }

    @Transactional
    @DeleteMapping("/{name}")
    public void delete(@PathVariable String name) {
        productService.delete(name);
    }
}
