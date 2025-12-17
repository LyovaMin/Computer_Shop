package by.lyofchik.AppSpring;

import by.lyofchik.AppSpring.Model.Entities.Sale;
import by.lyofchik.AppSpring.Repository.SaleRepository;
import by.lyofchik.AppSpring.Service.SaleService.SaleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SaleServiceTest {

    @Mock
    private SaleRepository repository;

    @InjectMocks
    private SaleService saleService;

    @Test
    void save_ShouldCallRepository() {
        Sale sale = new Sale();
        when(repository.save(sale)).thenReturn(sale);

        Sale savedSale = saleService.save(sale);

        assertNotNull(savedSale);
        verify(repository, times(1)).save(sale);
    }

    @Test
    void findByShopUserUserName_ShouldReturnList() {
        String name = "Lev";
        when(repository.findByUserUserName(name)).thenReturn(List.of(new Sale(), new Sale()));

        List<Sale> sales = saleService.findByShopUserUserName(name);

        assertEquals(2, sales.size());
        verify(repository, times(1)).findByUserUserName(name);
    }

    @Test
    void delete_ShouldCallRepositoryDeleteById() {
        int id = 5;
        doNothing().when(repository).deleteById(id);

        saleService.delete(id);

        verify(repository, times(1)).deleteById(id);
    }

    @Test
    void findAll_ShouldReturnAllSales() {
        when(repository.findAll()).thenReturn(List.of(new Sale(), new Sale()));

        List<Sale> result = saleService.findAll();

        assertEquals(2, result.size());
    }
}