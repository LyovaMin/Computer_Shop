package by.lyofchik.AppSpring.Controller.Rest;

import by.lyofchik.AppSpring.Model.Entities.User;
import by.lyofchik.AppSpring.Service.Hashing.PasswordHasher;
import by.lyofchik.AppSpring.Service.UserService.UserService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Slf4j
public class UserRestController {
    UserService userService;

    @GetMapping
    List<User> users() {
        return userService.findAll();
    }


    @PostMapping("/save")
    User saveUser(@RequestBody @Validated User user) {
        user.setPassword(PasswordHasher.hash(user.getPassword()));
        User save = userService.save(user);
        return save;
    }

    @GetMapping("/{name}")
    Optional<User> findUser(@PathVariable String name) {
        return userService.find(name);
    }

    @Transactional
    @DeleteMapping("/delete/{name}")
    boolean deleteUser(@PathVariable String name) {
        return userService.delete(name);
    }
}
