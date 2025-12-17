package by.lyofchik.AppSpring;

import by.lyofchik.AppSpring.Model.Entities.Product;
import by.lyofchik.AppSpring.Repository.ProductRepository;
import by.lyofchik.AppSpring.Service.ProductsService.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService productService;

    @Test
    void findByName_ShouldReturnProduct_WhenExists() {
        // Arrange
        String productName = "SSD Samsung 1TB";
        Product mockProduct = new Product();
                //Product.builder().productName(productName).price(129.0).build();
        mockProduct.setProductName(productName);
        mockProduct.setPrice(129);
        when(repository.findByProductName(productName)).thenReturn(Optional.of(mockProduct));

        // Act
        Optional<Product> result = productService.find(productName);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(productName, result.get().getProductName());
        verify(repository, times(1)).findByProductName(productName);
    }

    @Test
    void deleteByName_ShouldReturnTrue_OnSuccess() {
        // Act
        boolean result = productService.delete("Монитор");

        // Assert
        assertTrue(result);
        verify(repository, times(1)).deleteByName("Монитор");
    }

    @Test
    void findById_ShouldReturnProduct_WhenIdExists() {
        Product product = new Product();
                //Product.builder().id(1).productName("Test").build();
        product.setProductName("Test");
        product.setId(1);
        when(repository.findById(1)).thenReturn(Optional.of(product));

        Product result = productService.findById(1);

        assertEquals("Test", result.getProductName());
    }

    @Test
    void findById_ShouldThrowException_WhenIdDoesNotExist() {
        when(repository.findById(999)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> productService.findById(999));
    }

    @Test
    void findAll_ShouldReturnEmptyList_WhenNoProducts() {
        when(repository.findAll()).thenReturn(List.of());

        List<Product> result = productService.findAll();

        assertTrue(result.isEmpty());
    }

    @Test
    void findByCategory_ShouldReturnFilteredList() {
        String cat = "Мониторы";
        when(repository.findByCategory_CategoryName(cat)).thenReturn(List.of(new Product()));

        List<Product> result = productService.findByCategory_CategoryName(cat);

        assertFalse(result.isEmpty());
    }
}