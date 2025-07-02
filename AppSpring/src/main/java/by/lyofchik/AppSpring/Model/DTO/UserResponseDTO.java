package by.lyofchik.AppSpring.Model.DTO;

import by.lyofchik.AppSpring.Model.Entities.Role;
import lombok.*;

@Builder
public class UserResponseDTO {
    private String username;
    private Role role;
}
