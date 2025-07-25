package by.lyofchik.AppSpring.Configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncConfigurer;

import java.util.concurrent.*;

@Slf4j
@Configuration
@EnableAspectJAutoProxy
public class ExecutorServiceConfig implements AsyncConfigurer{
    @Bean
    @Scope("prototype")
    ExecutorService executor() {
        return new ThreadPoolExecutor(
                5,
                10,
                10L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(40), // возможная очередь потоков
                new ThreadPoolExecutor.CallerRunsPolicy() // Политика при переполнении
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
