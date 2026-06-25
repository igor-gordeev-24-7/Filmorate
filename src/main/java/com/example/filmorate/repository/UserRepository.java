package com.example.filmorate.repository;

import com.example.filmorate.model.User;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class UserRepository {
    private final Map<Integer, User> users = new HashMap<>();
    private int currentId = 1;

    public User addUser(User user) {
        user.setId(currentId++);
        users.put(user.getId(), user);
        return user;
    }

    public Collection<User> getAllUsers() {
        return users.values();
    }

    public Optional<User> getUserById(int id) {
        return Optional.ofNullable(users.get(id));
    }

    public User updateUser(User user) {
        if (users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            return user;
        }
        throw new RuntimeException("Пользователь с id " + user.getId() + " не найден");
    }
}
