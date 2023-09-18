package labs.savangard.spring.zad1.biblioteka.controllers;

import com.savangard.labs.rest.model.UserSchema.GetUsersResponse;
import labs.savangard.spring.zad1.biblioteka.services.implementations.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("library/users")
public class UserController {
    @Autowired
    private UserServiceImp userService;

    @GetMapping("/getAll")
    GetUsersResponse getAllUsers() {
        return userService.getAllUsers();
    }
}
