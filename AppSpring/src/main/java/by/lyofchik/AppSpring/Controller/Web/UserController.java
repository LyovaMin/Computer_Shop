package by.lyofchik.AppSpring.Controller.Web;

import by.lyofchik.AppSpring.Model.DTO.SaleDTO;
import by.lyofchik.AppSpring.Model.Entities.Product;
import by.lyofchik.AppSpring.Model.Entities.Sale;
import by.lyofchik.AppSpring.Model.Entities.User;
import by.lyofchik.AppSpring.Service.UserService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    UserService userService;

    @GetMapping
    public String user(Model model) {
        String authName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.find(authName).orElseThrow();
        List<Sale> sales = userService.findAllSales(user.getUserName());
        model.addAttribute("sales", sales);
        model.addAttribute("user", user);
        return "user/user";
    }

    @PostMapping("/changePassword")
    public String changePassword(@ModelAttribute User user,
                                 @RequestParam String oldPassword,
                                 @RequestParam String newPassword,
                                 RedirectAttributes redirectAttributes) {
        try{
            userService.changePassword(user, oldPassword, newPassword);
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/user";
    }

}
