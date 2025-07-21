package by.lyofchik.AppSpring.Service.MailService;

import by.lyofchik.AppSpring.Model.DTO.EmailRequest;
import jakarta.mail.MessagingException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
@Data
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String from;

    public void send(EmailRequest emailRequest) {
        try {
            var message = mailSender.createMimeMessage();
            var helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(from);
            helper.setTo(emailRequest.getEmail());
            helper.setSubject("Привет");
            helper.setText(emailRequest.getMessage());
            ByteArrayResource resource = new ByteArrayResource(emailRequest.getDocument().getBytes());
            helper.addAttachment("meow.jpg", resource, "image/jpeg");

            mailSender.send(message);
        } catch (MessagingException | IOException e) {
            log.error("\n=========================\n {}",e.getMessage(), e);
        }
    }
}
