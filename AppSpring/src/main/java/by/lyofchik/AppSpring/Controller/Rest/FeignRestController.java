package by.lyofchik.AppSpring.Controller.Rest;

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
    FeignThread feignThread;

    @GetMapping
    public Object findAll() {
        List<ProductDTO> list = new ArrayList<>();
        //CompletableFuture
        ExecutorService executor = Executors.newFixedThreadPool(3);
//        try {
//            List<Future<List<ProductDTO>>> future = new ArrayList<>();
//
//            for (int i = 0; i < 3; i++) {
//                future.add(executor.submit(feignThread));
//            }
//
//            for (Future<List<ProductDTO>> future1 : future) {
//                try {
//                    list.addAll(future1.get(50, TimeUnit.SECONDS));
//                } catch (InterruptedException | ExecutionException e) {
//                    log.error("Error by future {} ,{}", future1, e.getMessage());
//                }
//            }
//            if (list.isEmpty()) {
//                return ApiError.;
//            }
//            return list;
//        } catch (Exception e) {
//            try {
//                executor.shutdown();
//                executor.awaitTermination(10,TimeUnit.SECONDS);
//            } catch (InterruptedException ex) {
//                log.error("---------------");
//            }
//        } finally {
//            executor.shutdownNow();
//        }
        return  list;
    }
}
