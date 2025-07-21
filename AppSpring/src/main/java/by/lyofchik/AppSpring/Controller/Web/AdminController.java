package by.lyofchik.AppSpring.Controller.Web;

import by.lyofchik.AppSpring.Model.DTO.EmailRequest;
import by.lyofchik.AppSpring.Model.Entities.Product;
import by.lyofchik.AppSpring.Service.MailService.MailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;

@Slf4j
@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    MailService mailService;

    @GetMapping
    public String admin(Model model,
                        @ModelAttribute EmailRequest emailRequest) {
        model.addAttribute("emailRequest", emailRequest);
        return "adminPages/adminMainPage";
    }

    @PostMapping("/send")
    public String adminPost(@Validated @ModelAttribute EmailRequest emailRequest,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(error -> log.info(error.getDefaultMessage()));
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("emailRequest", emailRequest);
            return "redirect:/admin";
        }
        mailService.send(emailRequest);
        return "redirect:/admin";
    }
}
