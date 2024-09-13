package ru.javacode.student.controller;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.javacode.student.model.User;
import ru.javacode.student.model.view.Views;
import ru.javacode.student.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@Validated
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public @JsonView(Views.User.Short.class) List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public @JsonView(Views.User.Full.class) User getUser(@PathVariable long userId) {
        return userService.getUser(userId);
    }

    @PostMapping
    public @JsonView(Views.User.Full.class) User addUser(@RequestBody @JsonView(Views.User.New.class) @Valid User user) {
        return userService.addUser(user);
    }

    @PatchMapping
    public @JsonView(Views.User.Full.class) User updateUser(@RequestBody @JsonView(Views.User.Update.class) @Valid User user) {
        return userService.updateUser(user);
    }

}
