package by.lyofchik.AppSpring.Controller.Web;

import by.lyofchik.AppSpring.CustomException.CustomException;
import by.lyofchik.AppSpring.Mapper.UserMapper;
import by.lyofchik.AppSpring.Model.DTO.RegistrationDTO;
import by.lyofchik.AppSpring.Model.Entities.User;
import by.lyofchik.AppSpring.Service.UserService.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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
                               RedirectAttributes redirectAttributes) throws CustomException {
        log.info("Registration form received");
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("registrationDTO", registrationDTO);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/registration";
        }

        if(userService.find(registrationDTO.getUserName()).isPresent()){
            throw new CustomException(CustomException.ApiError.NICKNAME_IS_BUSY);
        }

        User user = userMapper.toUser(registrationDTO);
        userService.save(user);

        return "redirect:/shop";
    }

}
