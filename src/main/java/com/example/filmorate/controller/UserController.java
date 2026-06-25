package com.example.filmorate.controller;

import com.example.filmorate.model.User;
import com.example.filmorate.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userRepository.addUser(user);
    }

    @GetMapping
    public Collection<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @PutMapping
    public void updateUser(User user) {
        userRepository.updateUser(user);
    }
}
