package by.lyofchik.AppSpring.Configuration;

import feign.Logger;
import feign.Request;
import org.springframework.cloud.openfeign.clientconfig.FeignClientConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class TestServerFeignConfig implements FeignClientConfigurer {
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Request.Options options() {
        return new Request.Options(
                5000,
                10000
        );
    }

    // ErrorDecoder обработчик ошибкок exception decoder
    // RequestInterceptor заголовки типо "authorization"
    // Retryer для повторных попыток подключения
}
