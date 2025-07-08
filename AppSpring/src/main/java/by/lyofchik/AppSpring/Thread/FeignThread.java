package by.lyofchik.AppSpring.Thread;


import by.lyofchik.AppSpring.Configuration.FeignConfig;
import by.lyofchik.AppSpring.Model.DTO.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Callable;

@Component
@AllArgsConstructor
public class FeignThread implements Callable<List<ProductDTO>> {
    FeignConfig feign;

    @Override
    public List<ProductDTO> call() throws Exception {
        return feign.findAll();
    }
}
