package com.example.filmorate.service;

import com.example.filmorate.exeption.EntityNotFoundException;
import com.example.filmorate.model.User;
import com.example.filmorate.storage.user.UserStorage;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserStorage userStorage;

    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public User addUser(User user) {
        return userStorage.addUser(user);
    }

    public List<User> getAllUsers() {
        List<User> users = userStorage.getAllUsers();
        if (users.isEmpty()) {
            throw new EntityNotFoundException(
                    "Пользователи не найдены",
                    List.of("Список пользователей пуст"));
        }
        return users;
    }

    public User getUserById(Long id) {
        User user = userStorage.getUserById(id);

        if (user == null) {
            throw new EntityNotFoundException(
                    "Пользователь с id " + id + " не найден",
                    List.of("Проверьте корректный ли id"));
        }
        return user;
    }

    public User updateUser(Long id, User user) {
        User updatedUser = userStorage.updateUser(id, user);
        if (updatedUser == null){
            throw new EntityNotFoundException(
                    "Пользователь с id " + id + " не найден",
                    List.of("Проверьте корректный ли id"));
        }
        return updatedUser;
    }

    public void deleteUsers() {
        userStorage.deleteUsers();
    }

    public User deleteUserById(Long id) {
        User deletedUser = userStorage.deleteUserById(id);
        if (deletedUser == null) {
            throw new EntityNotFoundException(
                    "Пользователь с id " + id + " не найден",
                    List.of("Проверьте корректный ли id"));
        }
       return deletedUser;
    }

//    Приватный метод для проверки существования пользователя
    private User getUserOrThrow(Long id) {
        User user = userStorage.getUserById(id);
        if (user == null) {
            throw new EntityNotFoundException(
                    "Пользователь с id " + id + " не найден",
                    List.of("Проверьте корректный ли id"));
        }
        return user;
    }

    // Добавление в друзья (взаимное)
    public void addFriend(Long userId, Long friendId) {
        User user = getUserOrThrow(userId);
        User friend = getUserOrThrow(friendId);

        if (user.getFriends() == null) {
            user.setFriends(new HashSet<>());
        }
        if (friend.getFriends() == null) {
            friend.setFriends(new HashSet<>());
        }

        user.getFriends().add(friendId);
        friend.getFriends().add(userId);
    }

    // Удаление из друзей (взаимное)
    public void removeFriend(Long userId, Long friendId) {
        User user = getUserOrThrow(userId);
        User friend = getUserOrThrow(friendId);

        if (user.getFriends() == null) {
            user.setFriends(new HashSet<>());
        }
        if (friend.getFriends() == null) {
            friend.setFriends(new HashSet<>());
        }

        if (user.getFriends().contains(friendId)) {
            user.getFriends().remove(friendId);
            friend.getFriends().remove(userId);
        }
    }

    // Получение списка друзей пользователя
    public List<User> getFriends(Long userId) {
        User user = getUserOrThrow(userId);
        if (user == null) {
            throw new EntityNotFoundException(
                    "Пользователь с id " + userId + " не найден",
                    List.of("Проверьте корректный ли id"));
        }

        if (user.getFriends() == null || user.getFriends().isEmpty()) {
            return List.of();
        }

        return user.getFriends().stream()
                .map(userStorage::getUserById)
                .filter(friend -> friend != null)
                .collect(Collectors.toList());
    }

    // Получение списка общих друзей
    public List<User> getCommonFriends(Long userId, Long otherUserId) {
        User user = getUserOrThrow(userId);
        User otherUser = getUserOrThrow(otherUserId);

        if (user.getFriends() == null || user.getFriends().isEmpty() ||
                otherUser.getFriends() == null || otherUser.getFriends().isEmpty()) {
            return List.of();
        }

        Set<Long> commonFriendsIds = new HashSet<>(user.getFriends());
        commonFriendsIds.retainAll(otherUser.getFriends());

        return commonFriendsIds.stream()
                .map(userStorage::getUserById)
                .filter(friend -> friend != null)
                .collect(Collectors.toList());
    }
}