package by.lyofchik.AppSpring.Model.DTO;

import by.lyofchik.AppSpring.Validation.FileNotEmpty;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class EmailRequest {
    @FileNotEmpty(message = "Загрузите файл")
    private MultipartFile document;
//    @FileNotEmpty(message = "Загрузите файл")
//    private byte [] document;
    private String message;
    @Email(message = "Введите корректный email")
    private String email;

//    EmailRequest(MultipartFile document, String message, String email) {
//        this.email = email;
//        this.message = message;
//        try {
//            this.document = document.getBytes();
//        } catch (IOException e) {
//             throw new IOException();
//        }
//    }
}