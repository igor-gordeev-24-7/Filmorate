package com.example.filmorate.interfaces;

import org.apache.catalina.User;

import java.util.List;

public interface UserStorage {
    public User addUser(User user);
    public List<User> getAllUsers();
    public User getUserById(int id);
    public User updateUser( int id, User user);
    public User deleteUsers();
    public User deleteUserById();
}
