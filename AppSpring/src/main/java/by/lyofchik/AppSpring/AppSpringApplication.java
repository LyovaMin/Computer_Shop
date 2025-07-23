package by.lyofchik.AppSpring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@Slf4j
@SpringBootApplication
@EnableFeignClients
@EnableCaching
@EnableAsync
public class AppSpringApplication {
	public static void main(String[] args) {
		SpringApplication.run(AppSpringApplication.class, args);
	}
}
