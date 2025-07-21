package by.lyofchik.AppSpring.Feign;

import by.lyofchik.AppSpring.Configuration.TestServerFeignConfig;
import by.lyofchik.AppSpring.Model.DTO.ProductDTO;
import by.lyofchik.AppSpring.Model.DTO.ProductRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "meow", url = "localhost:9090", configuration = TestServerFeignConfig.class )
public interface TestServerFeign {
    @GetMapping("/main")
    List<ProductDTO> findAll();

    @PostMapping("/get-by-id")
    List<ProductDTO> getById(ProductRequestDto requestDto);
}
