package by.lyofchik.AppSpring.Controller.Web;

import by.lyofchik.AppSpring.Model.DTO.EmailRequest;
import by.lyofchik.AppSpring.Model.Entities.Product;
import by.lyofchik.AppSpring.Service.MailService.MailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Slf4j
@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    MailService mailService;

    @GetMapping
    public String admin(){
        return "adminPages/adminMainPage";
    }

    @PostMapping("/send")
    public String adminPost(@ModelAttribute EmailRequest emailRequest){
        mailService.send(emailRequest);
        return "redirect:/admin";
    }
}
