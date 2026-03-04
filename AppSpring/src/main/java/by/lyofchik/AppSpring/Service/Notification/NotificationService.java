package by.lyofchik.AppSpring.Service.Notification;

import com.google.firebase.messaging.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {
    public void sendNotification(String token, String title, String body) {
        Message message = Message.builder()
                .setToken(token)
                .setNotification(Notification.builder()
                        .setTitle(title)
                        .setBody(body)
                        .build())
                .build();

        try{
            FirebaseMessaging.getInstance().send(message);
        }catch(FirebaseMessagingException e){
            log.error("Firebase messaging error {}", e.getMessage());
        }
    }
}
