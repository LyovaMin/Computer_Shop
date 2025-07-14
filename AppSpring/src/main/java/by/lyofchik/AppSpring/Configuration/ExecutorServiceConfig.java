package by.lyofchik.AppSpring.Configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncConfigurer;

import java.util.concurrent.*;

@Slf4j
@Configuration
@EnableAspectJAutoProxy
public class ExecutorServiceConfig implements AsyncConfigurer{
    @Bean
    ExecutorService executor() {
        return new ThreadPoolExecutor(
                1,
                10,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(20) // возможная очередь потоков
        );
        // ThreadPoolExecutor для обработки отказа при переполнении очереди
        // можно переопределить AsyncConfigurer методы
    }

    @Override
    public Executor getAsyncExecutor() {
        return executor();
    }

    @Override
    public AsyncUncaughtExceptionHandler  getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) -> {
            log.error("Ошибка в асинхронном методе: {}", method.getName());
            log.error("Исключение: {}", ex.getMessage());
        };
    }
}
