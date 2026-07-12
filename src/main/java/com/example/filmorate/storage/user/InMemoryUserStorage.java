package com.example.filmorate.storage.user;

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
        return new ArrayList<>(users.values());
    }

    @Override
    public User getUserById(Long id) {
        return users.get(id);
    }

    @Override
    public User updateUser(Long id, User user) {
        if (users.containsKey(id)){
            user.setId(id);
            users.put(id, user);
            return user;
        }
        return null;
    }

    @Override
    public void deleteUsers() {
        users.clear();
    }

    @Override
    public User deleteUserById(Long id) {
        return users.remove(id);
    }
}
