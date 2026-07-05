package com.example.filmorate.storage.user;

import com.example.filmorate.exeption.EntityNotFoundException;
import com.example.filmorate.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryUserStorage implements UserStorage {
    private final Map<Long, User> users = new HashMap<>();
    private Long currentId = 1L;

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
    public User getUserById(Long id) {
        if (users.containsKey(id)) {
            return users.get(id);
        }
        throw new EntityNotFoundException(
                "Пользователь с id " + id + " не найден",
                List.of("Проверьте корректный ли id"));
    }

    @Override
    public User updateUser(Long id, User user) {
        if (users.containsKey(id)){
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
    public User deleteUserById(Long id) {
        if (users.containsKey(id)) {
            return users.remove(id);
        }
        throw new EntityNotFoundException(
                "Пользователь с id " + id + " не найден",
                List.of("Проверьте корректный ли id"));
    }
}
