package by.lyofchik.AppSpring.Model.DTO;

import by.lyofchik.AppSpring.Model.Entities.Role;
import by.lyofchik.AppSpring.Model.Entities.Sale;
import lombok.*;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private int id;
    private String userName;
    private Role role;
    private List<SaleDTO> sales;
}
