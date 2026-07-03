package com.example.filmorate.repository;

import com.example.filmorate.exeption.EntityNotFoundException;
import com.example.filmorate.model.User;
import org.springframework.stereotype.Component;

import java.util.*;

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
        if (!users.isEmpty()) {
            return new ArrayList<>(users.values());
        }
        throw new EntityNotFoundException("Пользователи не найдены", List.of("Cписок пуст"));
    }

    public User getUserById(int id) {
        if (users.containsKey(id)) {
            return users.get(id);
        }
        throw new EntityNotFoundException("Пользователь с id " + users.get(id) + " не найден", List.of("Проверьте корректный ли id"));
    }

    public User updateUser(int id ,User user) {
        if (users.containsKey(id)) {
            user.setId(id);
            users.put(id, user);
            return user;
        }
        throw new EntityNotFoundException("Пользователь с id " + id + " не найден", List.of("Проверьте корректный ли id"));
    }
}
