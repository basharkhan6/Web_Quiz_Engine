package engine.controller;

import engine.model.User;
import engine.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/api/register", consumes = "application/json")
    public void registration(@RequestBody @Valid User user) {
        userService.saveNewUser(user);
    }
}
