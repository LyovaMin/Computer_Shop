package by.lyofchik.AppSpring.Model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
public class RegistrationDTO {
    @Size(min = 3, message = "Имя должно состоять мимимум из 3 символов")
    @NotBlank(message = "Имя не может быть пустым")
    private String userName;

    @Size(min = 3, message = "Пароль должен состоять мимимум из 3 символов")
    @NotBlank(message = "Пароль не может быть пустым")
    private String password;
}
