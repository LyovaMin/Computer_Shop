package by.lyofchik.AppSpring.Thread;


import by.lyofchik.AppSpring.Feign.TestServerFeign;
import by.lyofchik.AppSpring.Model.DTO.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Callable;

@Slf4j
@Component
@AllArgsConstructor
public class FeignThread implements Callable<List<ProductDTO>> {
    TestServerFeign feign;

    @Override
    public List<ProductDTO> call() throws Exception {
//        User user = userRepository.getBuId();
//        List<ProductDTO> byId = feign.getById(ProductRequestDto.builder().id("1").build());
        return feign.findAll();
    }
}
