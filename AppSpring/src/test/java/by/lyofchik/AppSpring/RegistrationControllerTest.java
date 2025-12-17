package by.lyofchik.AppSpring;

import by.lyofchik.AppSpring.Mapper.UserMapper;
import by.lyofchik.AppSpring.Model.Entities.Role;
import by.lyofchik.AppSpring.Model.Entities.User;
import by.lyofchik.AppSpring.Service.UserService.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private UserMapper userMapper;

    @Test
    void registrationPage_ShouldReturnView() throws Exception {
        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("authentification/registration"));
    }

    @Test
    void registration_Success_ShouldRedirectToShop() throws Exception {
        // Данные для теста
        String username = "NewUser";
        String password = "password123";
        User mockUser = User.builder()
                .userName(username)
                .password(password)
                .role(Role.USER)
                .build();

        // Настройка моков
        when(userService.find(username)).thenReturn(Optional.empty());
        when(userMapper.toUser(any())).thenReturn(mockUser);
        when(userService.save(any())).thenReturn(mockUser);

        // Выполнение запроса
        mockMvc.perform(post("/registration")
                        .param("userName", username)
                        .param("password", password)
                        .with(csrf())) // Не забываем CSRF, так как Spring Security включен
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/shop"));

        // Проверяем, что методы сервиса вызывались
        verify(userService, times(1)).save(any());
    }

    @Test
    void registration_ShortPassword_ShouldFailValidation() throws Exception {
        // Пароль короче 3 символов (согласно вашему RegistrationDTO)
        mockMvc.perform(post("/registration")
                        .param("userName", "ValidName")
                        .param("password", "12")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/registration"))
                .andExpect(flash().attributeExists("errors"));

        // Сохранение не должно вызываться
        verify(userService, never()).save(any());
    }

    @Test
    void registration_UserAlreadyExists_ShouldRedirectWithErrorMessage() throws Exception {
        String existingUser = "Anton"; // Судя по вашим скринам БД, Антон уже есть

        when(userService.find(existingUser)).thenReturn(Optional.of(new User()));

        mockMvc.perform(post("/registration")
                        .param("userName", existingUser)
                        .param("password", "somePassword")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/registration"))
                .andExpect(flash().attributeExists("errors"));

        verify(userService, never()).save(any());
    }

    @Test
    void registration_EmptyUserName_ShouldFail() throws Exception {
        mockMvc.perform(post("/registration")
                        .param("userName", "")
                        .param("password", "validPass")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("errors"));
    }

    @Test
    void registration_WithoutCsrf_ShouldReturnForbidden() throws Exception {
        mockMvc.perform(post("/registration")
                        .param("userName", "NewUser")
                        .param("password", "password123"))
                .andExpect(status().isForbidden());
    }
}