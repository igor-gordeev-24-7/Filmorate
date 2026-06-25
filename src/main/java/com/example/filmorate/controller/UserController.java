package com.example.filmorate.controller;

import com.example.filmorate.model.User;
import com.example.filmorate.model.UserStorage;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserStorage userStorage;

    public UserController(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userStorage.addUser(user);
    }

    @GetMapping
    public Collection<User> getAllUsers() {
        return userStorage.getAllUsers();
    }
}
