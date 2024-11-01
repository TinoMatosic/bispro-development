package dev.bispro.services.impl;

import dev.bispro.domain.User;
import dev.bispro.persistence.UserRepository;
import dev.bispro.services.UserService;
import dev.bispro.services.exceptions.ServiceLayerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUserId(Long userId) {
        validateArguments(userId, null);
        return userRepository.findById(userId)
                .orElseThrow(() -> ServiceLayerException.userNotFound("User not found with ID: " + userId));
    }

    @Override
    public User createUser(User user) {
        validateArguments(null, user);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, User user) {
        validateArguments(userId, user);
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            try {
                User updatedUser = existingUser.get();
                updatedUser.setFirstname(user.getFirstname());
                updatedUser.setLastname(user.getLastname());
                updatedUser.setEmail(user.getEmail());
                updatedUser.setPassword(user.getPassword());
                updatedUser.setRole(user.getRole());
                updatedUser.setPlan(user.getPlan());
                return userRepository.save(updatedUser);
            } catch (Exception e) {
                throw ServiceLayerException.forUpdateError("Error Updating User with Id [" + userId + "]: " + e.getMessage());
            }
        } else {
            throw ServiceLayerException.userNotFound("User not found with ID: " + userId);
        }
    }

    @Override
    public void deleteUser(Long userId) {
        validateArguments(userId, null);
        try {
            userRepository.deleteById(userId);
        } catch (Exception e) {
            throw ServiceLayerException.forDeleteError("Error Deleting User with Id [" + userId + "]: " + e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw ServiceLayerException.forGetError("Error Retrieving All Users");
        }
    }

    private void validateArguments(Long userId, User user) {
        if (userId != null && userId < 0) {
            throw ServiceLayerException.forInvalidArgument("Invalid user ID: " + userId);
        }
        if (user != null) {
            if (user.getFirstname() == null || user.getLastname() == null ||
                    user.getEmail() == null || user.getPassword() == null) {
                throw ServiceLayerException.forInvalidArgument("User object contains null fields");
            }
        }
    }
}
