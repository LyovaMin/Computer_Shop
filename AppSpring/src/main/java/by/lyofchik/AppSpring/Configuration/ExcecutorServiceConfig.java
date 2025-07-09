package by.lyofchik.AppSpring.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class ExcecutorServiceConfig {
    @Bean
    ExecutorService executor() {
        return new ThreadPoolExecutor(
                1,
                10,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100) // возможная очередь потоков
        );
    }
}
