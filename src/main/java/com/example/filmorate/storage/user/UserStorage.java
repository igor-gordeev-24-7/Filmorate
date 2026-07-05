package com.example.filmorate.storage.user;

import com.example.filmorate.model.User;

import java.util.List;

public interface UserStorage {
    User addUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    User updateUser(Long id, User user);
    void deleteUsers();
    User deleteUserById(Long id);
}
