package by.lyofchik.AppSpring.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificationResponse {
    private int status;
    private String message;
}
