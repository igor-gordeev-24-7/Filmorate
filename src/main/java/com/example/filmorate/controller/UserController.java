package com.example.filmorate.controller;

import com.example.filmorate.model.User;
import com.example.filmorate.storage.user.InMemoryUserStorage;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    private final InMemoryUserStorage inMemoryUserStorage;

    public UserController(InMemoryUserStorage inMemoryUserStorage) {
        this.inMemoryUserStorage = inMemoryUserStorage;
    }

    @PostMapping
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        User savedUser = inMemoryUserStorage.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping
    public Collection<User> getAllUsers() {
        return inMemoryUserStorage.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return inMemoryUserStorage.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(
            @PathVariable int id,
            @Valid @RequestBody User user) {
        return inMemoryUserStorage.updateUser(id, user);
    }

    @DeleteMapping()
    public ResponseEntity<Map<String, String>> deleteUsers() {
        inMemoryUserStorage.deleteUsers();
        return ResponseEntity.ok(Map.of("message", "Все записи успешно удалены"));
    }

    @DeleteMapping("/{id}")
    public User deleteUserById(@PathVariable int id) {
        return inMemoryUserStorage.deleteUserById(id);
    }
}
