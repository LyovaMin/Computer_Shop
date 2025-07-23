package by.lyofchik.AppSpring.Model.DTO;

import by.lyofchik.AppSpring.Validation.FileNotEmpty;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@Getter
public class EmailRequest {
//    @FileNotEmpty(message = "Загрузите файл")
//    private MultipartFile document;
    private byte [] document;
    private String message;
    @Email(message = "Введите корректный email")
    private String email;

    EmailRequest(MultipartFile document, String message, String email) {
        if (document != null) {
            try {
                this.document = document.getBytes();
                this.email = email;
                this.message = message;
            } catch (IOException e) {
                 throw new RuntimeException(e);
            }
        }
    }
}
