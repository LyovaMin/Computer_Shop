package by.lyofchik.AppSpring.Controller.Web;

import by.lyofchik.AppSpring.CustomException.CustomException;
import by.lyofchik.AppSpring.Mapper.UserMapper;
import by.lyofchik.AppSpring.Model.DTO.RegistrationDTO;
import by.lyofchik.AppSpring.Model.Entities.User;
import by.lyofchik.AppSpring.Service.UserService.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;


@Slf4j
@Controller
@RequestMapping("/registration")
@AllArgsConstructor
public class RegistrationController {
    UserService userService;
    UserMapper userMapper;

    @GetMapping
    public String registrationPage(@ModelAttribute RegistrationDTO registrationDTO,
                                   @ModelAttribute Object errors) {
        return "authentification/registration";
    }

    @PostMapping
    public String registration(@Validated @ModelAttribute RegistrationDTO registrationDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               HttpServletRequest request)  {
        log.info("Registration form received");
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("registrationDTO", registrationDTO);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/registration";
        }

        if (userService.find(registrationDTO.getUserName()).isPresent()) {
            redirectAttributes.addFlashAttribute("registrationDTO", registrationDTO);
            redirectAttributes.addFlashAttribute("errors", "Пользователь с таким именем уже существует");
            return "redirect:/registration";
        }

//        if(userService.find(registrationDTO.getUserName()).isPresent()){
//            throw new CustomException(CustomException.ApiError.NICKNAME_IS_BUSY.getDefaultMessage());
//        }

        User user = userMapper.toUser(registrationDTO);
        userService.save(user);

        try {
            // Создаем токен аутентификации.
            // В качестве credentials передаем пароль, в качестве authorities — роли пользователя
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    user.getUserName(),
                    registrationDTO.getPassword(),
                    Collections.singleton(user.getRole()) // Убедитесь, что в сущности User реализован метод getAuthorities()
            );

            // Устанавливаем аутентификацию в SecurityContext
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(token);
            SecurityContextHolder.setContext(context);

            // Сохраняем контекст в сессию, чтобы Spring Security "увидел" логин на следующей странице
            request.getSession().setAttribute(
                    HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                    context
            );

            log.info("User {} automatically logged in after registration", user.getUserName());
        } catch (Exception e) {
            log.error("Error while automatic login", e);
            return "redirect:/login"; // Если что-то пошло не так, отправляем на обычный вход
        }

        return "redirect:/shop";
    }

}
