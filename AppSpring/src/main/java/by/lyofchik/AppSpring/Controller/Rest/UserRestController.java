package by.lyofchik.AppSpring.Controller.Rest;

import by.lyofchik.AppSpring.Model.DTO.UserResponseDTO;
import by.lyofchik.AppSpring.Model.Entities.User;
import by.lyofchik.AppSpring.Repository.UserRepository;
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
    List<UserResponseDTO> users() {
        return userService.findAll();
    }


    @PostMapping("/save")
    User saveUser(@RequestBody @Validated User user) {
        user.setPassword(PasswordHasher.hash(user.getPassword()));
        return userService.save(user);
    }

    @GetMapping("/{name}")
    Optional<User> findUser(@PathVariable String name) {
        return userService.find(name);
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    boolean deleteUser(@PathVariable int id) {
        userService.deleteById(id);
        return true;
    }
}
