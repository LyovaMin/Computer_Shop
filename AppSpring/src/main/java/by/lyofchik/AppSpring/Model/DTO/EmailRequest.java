package by.lyofchik.AppSpring.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Base64;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmailRequest {

    //base64
    private byte [] document;
    private String message;
    private String email;

}
