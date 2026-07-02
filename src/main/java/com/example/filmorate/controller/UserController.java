package com.example.filmorate.controller;

import com.example.filmorate.model.User;
import com.example.filmorate.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userRepository.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id,
                           @Valid @RequestBody User user) {
        return userRepository.updateUser(user);
    }
}
