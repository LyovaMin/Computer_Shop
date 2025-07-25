package by.lyofchik.AppSpring.Controller.Rest;

import by.lyofchik.AppSpring.Model.Entities.Product;
import by.lyofchik.AppSpring.Service.ProductsService.ProductService;
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
    ExecutorService executor;
    ProductService productService;

    @GetMapping
    public Object findAll() {
        List<Product> list = new ArrayList<>();
        List<CompletableFuture<Product>> futures = new ArrayList<>();

        ExecutorService executorService = Executors.newFixedThreadPool(5);


        for (int i = 0; i < 52; i++) {
            int finalI = i;
            CompletableFuture<Product> future = CompletableFuture.supplyAsync(() -> {
                try{
                    TimeUnit.SECONDS.sleep(3);
                    return productService.findById(finalI);
                } catch (RuntimeException | InterruptedException e) {
                    log.error(e.getMessage());
                    throw new RuntimeException(e);
                }
                    },
                    executor);
            futures.add(future);
        }

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> {
                    futures.stream()
                            .filter(f -> {
                        try {
                            return f.get() != null;
                        } catch (InterruptedException | ExecutionException e) {
                            log.error(e.getMessage());
                            throw new RuntimeException(e);
                        }
                    })
                            .forEach(future -> {
                        try{
                            list.add(future.get());
                        } catch (RuntimeException | ExecutionException | InterruptedException e) {
                            log.error(e.getMessage());
                        }
                    });
                    return list;


                });


//        for (int i = 0; i < 20; i++) {
//            CompletableFuture<List<ProductDTO>> future = CompletableFuture.supplyAsync(() -> {
//                try {
//                    return feignThread.call();
//                }catch (Exception e){
//                    log.error(e.getMessage());
//                    return null;
//                }
//            }, executor);
//            futures.add(future);
//        }
//
//        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
//                .thenApply(v -> {
//                    futures.forEach(future -> {
//                        try{
//                            log.info(future.toString());
//                            list.addAll(future.join());
//                        }catch (Exception e){
//                            log.error(e.getMessage());
//                        }
//                    });
//                    //executor.shutdown();
//                    return list;
//                });


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
    }
}
