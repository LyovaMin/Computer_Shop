package by.lyofchik.AppSpring.Service.MailService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Data
public class MailService {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String from;

    public void send(String email, String messageText) {

        var message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Привет");
        message.setText(messageText);
        message.setFrom(from);
        mailSender.send(message);
    }
}
