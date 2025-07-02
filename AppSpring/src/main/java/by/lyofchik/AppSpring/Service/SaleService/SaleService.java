package by.lyofchik.AppSpring.Service.SaleService;

import by.lyofchik.AppSpring.Model.Entities.Sale;
import by.lyofchik.AppSpring.Repository.SaleRepository;
import by.lyofchik.AppSpring.Service.EntityInterfaces.FindAllEntities;
import by.lyofchik.AppSpring.Service.EntityInterfaces.SaveEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class SaleService implements DeleteSale,
        FindUserSales,
        FindAllEntities<Sale>,
        SaveEntity<Sale> {

    private final SaleRepository repository;

    @Transactional
    @Override
    public void delete(int saleId) {
        repository.deleteById(saleId);
    }

    @Override
    public List<Sale> findByShopUserUserName(String username) {
        return repository.findByUserUserName(username);
    }

    @Override
    public Sale save(Sale sale) {
        return repository.save(sale);
    }

    @Override
    public List<Sale> findAll() {
        return repository.findAll();
    }
}
