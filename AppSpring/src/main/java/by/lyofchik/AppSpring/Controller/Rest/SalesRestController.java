package by.lyofchik.AppSpring.Controller.Rest;

import by.lyofchik.AppSpring.Model.Entities.Sale;
import by.lyofchik.AppSpring.Service.SaleService.SaleService;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
@AllArgsConstructor
@Transactional
public class SalesRestController {
    SaleService saleService;

    @GetMapping
    List<Sale> findAllSales(){
        return saleService.findAll();
    }

    @GetMapping("/{username}")
    List<Sale> findAllSales(@PathVariable String username){
        return saleService.findByShopUserUserName(username);
    }

    @PostMapping("save_sale")
    void saveSale(@RequestBody Sale sale) {
        saleService.save(sale);
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    void deleteSale(@PathVariable int id){
        saleService.delete(id);
    }
}
