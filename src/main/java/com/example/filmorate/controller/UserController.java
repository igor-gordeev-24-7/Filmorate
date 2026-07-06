package com.example.filmorate.controller;

import com.example.filmorate.model.User;
import com.example.filmorate.service.UserService;
import com.example.filmorate.storage.user.InMemoryUserStorage;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    private final InMemoryUserStorage inMemoryUserStorage;
    private final UserService userService;

    public UserController(InMemoryUserStorage inMemoryUserStorage, UserService userService) {
        this.inMemoryUserStorage = inMemoryUserStorage;
        this.userService = userService;
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
    public User getUserById(@PathVariable Long id) {
        return inMemoryUserStorage.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(
            @PathVariable Long id,
            @Valid @RequestBody User user) {
        return inMemoryUserStorage.updateUser(id, user);
    }

    @DeleteMapping()
    public ResponseEntity<Map<String, String>> deleteUsers() {
        inMemoryUserStorage.deleteUsers();
        return ResponseEntity.ok(Map.of("message", "Все записи успешно удалены"));
    }

    @DeleteMapping("/{id}")
    public User deleteUserById(@PathVariable Long id) {
        return inMemoryUserStorage.deleteUserById(id);
    }

    @PutMapping("/{id}/friends/{friendId}")
    public void addFriend (@PathVariable Long id, @PathVariable Long friendId) {
        userService.addFriend(id, friendId);
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public void removeFriend(@PathVariable Long id, @PathVariable Long friendId) {
        userService.removeFriend(id, friendId);
    }

    @GetMapping("{id}/friends")
    public List<User> getFriend(@PathVariable Long id) {
        return userService.getFriends(id);
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    public List<User> getCommonFriends(@PathVariable Long id, @PathVariable Long otherId) {
        return userService.getCommonFriends(id, otherId);
    }
 }

 