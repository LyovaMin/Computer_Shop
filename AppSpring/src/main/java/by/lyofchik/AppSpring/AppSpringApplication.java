package by.lyofchik.AppSpring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class AppSpringApplication {
	public static void main(String[] args) {
		SpringApplication.run(AppSpringApplication.class, args);
		log.info("Application Started");
	}
}
