package dev.bispro.services;

import dev.bispro.domain.User;

import java.util.List;

public interface UserService {

    User findByUserId(Long userId);

    User updateUser(Long userId, User user);

    void deleteUser(Long userId);

    List<User> getAllUsers();

    User login(String email, String password);

    User signup(User user);

}
