package by.lyofchik.AppSpring.Controller.Web;

import by.lyofchik.AppSpring.Mapper.UserMapper;
import by.lyofchik.AppSpring.Model.Entities.Role;
import by.lyofchik.AppSpring.Model.Entities.User;
import by.lyofchik.AppSpring.Service.UserService.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/users")
@AllArgsConstructor
@Slf4j
public class AdminUsersController {
    UserService userService;
    UserMapper mapper;

    @GetMapping
    public String users(Model model){
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", Role.values());
        return "admin/users";
    }

    @PostMapping("/delete/{name}")
    public String deleteUser(@PathVariable String name){
        userService.delete(name);
        return "redirect:/admin/users";
    }

    @PostMapping("/edit")
    public String editUser(@RequestParam("id") Integer id,
                           @RequestParam("username") String username,
                           @RequestParam("role") Role role) {
        User existingUser = userService.findById(id);

        if (existingUser != null) {
            existingUser.setUserName(username);
            existingUser.setRole(role);

            userService.save(existingUser);
        }

        return "redirect:/admin/users";
    }
}
