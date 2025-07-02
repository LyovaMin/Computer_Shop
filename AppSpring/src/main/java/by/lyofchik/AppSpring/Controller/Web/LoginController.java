package by.lyofchik.AppSpring.Controller.Web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping
    public String login() {
        log.info("login page opened");
        return "authentification/login";
    }
}
