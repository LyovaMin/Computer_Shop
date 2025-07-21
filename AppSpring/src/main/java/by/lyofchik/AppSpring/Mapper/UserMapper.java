package by.lyofchik.AppSpring.Mapper;

import by.lyofchik.AppSpring.Configuration.MapperConfig;
import by.lyofchik.AppSpring.Model.DTO.RegistrationDTO;
import by.lyofchik.AppSpring.Model.DTO.UserResponseDTO;
import by.lyofchik.AppSpring.Model.Entities.Role;
import by.lyofchik.AppSpring.Model.Entities.User;
import by.lyofchik.AppSpring.Service.Hashing.PasswordHasher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserMapper {
    private static ModelMapper mapper;

    public User toUser(RegistrationDTO registrationDTO){
        return User.builder()
                .userName(registrationDTO.getUserName())
                .password(PasswordHasher.hash(registrationDTO.getPassword()))
                .role(Role.USER)
                .build();
    }

    public static UserResponseDTO toResponseDTO(User user){
        return mapper.map(user, UserResponseDTO.class);
    }
}
