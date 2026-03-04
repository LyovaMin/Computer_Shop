package by.lyofchik.AppSpring.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@Configuration
public class NotificationConfig {
    @Value("${app.firebase-configuration-file}")
    private String firebaseKeys;

    @Bean
    public FirebaseApp config()  {
        try (FileInputStream fis = new FileInputStream(firebaseKeys)) {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(fis))
                    .build();

            return FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            log.error("Error with FireBase config {}",e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
