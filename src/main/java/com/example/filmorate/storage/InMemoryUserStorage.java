package com.example.filmorate.storage;

import com.example.filmorate.exeption.EntityNotFoundException;
import com.example.filmorate.model.Film;
import com.example.filmorate.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryUserStorage implements UserStorage {
    private final Map<Integer, User> users = new HashMap<>();
    private int currentId = 1;

    @Override
    public User addUser(User user) {
        user.setId(currentId++);
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        if (!users.isEmpty()) {
            return new ArrayList<>(users.values());
        }
        throw new EntityNotFoundException(
                "Пользователи не найдены",
                List.of("Cписок пуст"));
    }

    @Override
    public User getUserById(int id) {
        if (users.containsKey(id)) {
            return users.get(id);
        }
        throw new EntityNotFoundException(
                "Пользователь с id " + id + " не найден",
                List.of("Проверьте корректный ли id"));
    }

    @Override
    public User updateUser(int id ,User user) {
        if (users.containsKey(id)) {
            user.setId(id);
            users.put(id, user);
            return user;
        }
        throw new EntityNotFoundException(
                "Пользователь с id " + id + " не найден",
                List.of("Проверьте корректный ли id"));
    }

    @Override
    public void deleteUsers() {
        if (users.isEmpty()) {
            throw new EntityNotFoundException(
                    "Записи не найдены",
                    List.of("Удаление невозможно"));
        }
        users.clear();
    }

    @Override
    public User deleteUserById(int id) {
        if (users.containsKey(id)) {
            return users.remove(id);
        }
        throw new EntityNotFoundException(
                "Пользователь с id " + id + " не найден",
                List.of("Проверьте корректный ли id"));
    }
}
