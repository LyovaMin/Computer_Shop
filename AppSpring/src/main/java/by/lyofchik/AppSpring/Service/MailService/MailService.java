package by.lyofchik.AppSpring.Service.MailService;

import by.lyofchik.AppSpring.Model.DTO.EmailRequest;
import by.lyofchik.AppSpring.Model.DTO.EmailResponseDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.pretty.MessageHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String from;

    public void send(String email, String messageText) {

        var message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Привет");
        message.setText(messageText);
        message.setFrom(from);
        MessageHelper messageHelper = new MessageHelper(message);
        messageHelper.
                mailSender.send(message);
    }


    public Optional<EmailResponseDto> sendEmail(EmailRequest emailRequest) {
        return Optional.empty();
    }
}
