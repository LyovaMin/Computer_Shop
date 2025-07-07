package by.lyofchik.AppSpring.Controller.Rest;

import by.lyofchik.AppSpring.Configuration.FeignConfig;
import by.lyofchik.AppSpring.Model.DTO.ProductDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feign")
@AllArgsConstructor
public class FeignRestController {
    FeignConfig feign;

    @GetMapping
    public List<ProductDTO> findAll() {
        return feign.findAll();
    }
}
