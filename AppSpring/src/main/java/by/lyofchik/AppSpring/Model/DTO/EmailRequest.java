package by.lyofchik.AppSpring.Model.DTO;

import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class EmailRequest {
    private MultipartFile document;
    private String message;
    private String email;

//    EmailRequest(MultipartFile document, String message, String email) {
//        this.email = email;
//        this.message = message;
//        try {
//            this.document = document.getBytes();
//        } catch (IOException e) {
//            this.document = null;
//        }
//    }
}