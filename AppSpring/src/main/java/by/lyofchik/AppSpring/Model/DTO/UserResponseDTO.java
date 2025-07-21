package by.lyofchik.AppSpring.Model.DTO;

import by.lyofchik.AppSpring.Model.Entities.Role;
import by.lyofchik.AppSpring.Model.Entities.Sale;
import lombok.*;

import java.util.List;

@Builder
public class UserResponseDTO {
    private String username;
    private Role role;
    private List<Sale> sales;
}
