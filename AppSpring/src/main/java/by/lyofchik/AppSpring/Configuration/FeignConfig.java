package by.lyofchik.AppSpring.Configuration;

import by.lyofchik.AppSpring.Model.DTO.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "meow", url = "localhost:9090")
public interface FeignConfig {
    @GetMapping("/main")
    List<ProductDTO> findAll();
}
