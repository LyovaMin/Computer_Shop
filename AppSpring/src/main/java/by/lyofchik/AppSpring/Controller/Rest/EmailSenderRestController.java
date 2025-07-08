package by.lyofchik.AppSpring.Controller.Rest;

import by.lyofchik.AppSpring.Service.MailService.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class EmailSenderRestController {
    @Autowired
    MailService mailService;

    @GetMapping
    public void sendEmail(@RequestParam String email,  @RequestParam String message) {
        mailService.send(email, message);
    }
}
