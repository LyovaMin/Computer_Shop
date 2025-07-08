package by.lyofchik.AppSpring.Controller.Rest;

import by.lyofchik.AppSpring.Configuration.FeignConfig;
import by.lyofchik.AppSpring.Model.DTO.ProductDTO;
import by.lyofchik.AppSpring.Thread.FeignThread;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.*;

@Slf4j
@RestController
@RequestMapping("/feign")
@AllArgsConstructor
public class FeignRestController {
    FeignConfig feign;
    FeignThread feignThread;

    @GetMapping
    public List<ProductDTO> findAll() throws Exception {
        log.info("Вызван Feign");
        List<ProductDTO> list = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<List<ProductDTO>>> future = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            future.add(executor.submit(feignThread));
        }

        for (Future<List<ProductDTO>> future1 : future) {
            list.addAll(future1.get());
        }

        return list;
    }
}
