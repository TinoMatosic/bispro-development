// dev.bispro.services.impl.UserServiceImpl.java
package dev.bispro.services.impl;

import dev.bispro.domain.Role;
import dev.bispro.domain.User;
import dev.bispro.dtos.UserDTO;
import dev.bispro.dtos.UserRegisterDTO;
import dev.bispro.dtos.UserLoginDTO;
import dev.bispro.persistence.UserRepository;
import dev.bispro.services.UserService;
import dev.bispro.services.exceptions.ServiceLayerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO findByUserId(Long userId) {
        validateUserId(userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> ServiceLayerException.notFound("User not found with ID: " + userId));
        return toUserDTO(user);
    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        validateUserId(userId);
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setFirstname(userDTO.getFirstName());
            user.setLastname(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            user.setRole(userDTO.getRole());
            user.setPlan(userDTO.getPlan());
            return toUserDTO(userRepository.save(user));
        } else {
            throw ServiceLayerException.notFound("User not found with ID: " + userId);
        }
    }

    @Override
    public void deleteUser(Long userId) {
        validateUserId(userId);
        userRepository.deleteById(userId);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO signup(UserRegisterDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            throw ServiceLayerException.forCreateError("User with this email already exists");
        }
        User user = new User(
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getEmail(),
                passwordEncoder.encode(userDTO.getPassword()),
                Role.USER,
                userDTO.getPlan(),
                null
        );
        return toUserDTO(userRepository.save(user));
    }

    @Override
    public UserDTO login(UserLoginDTO loginUserDTO) {
        User user = userRepository.findByEmail(loginUserDTO.getEmail());
        if (user == null || !passwordEncoder.matches(loginUserDTO.getPassword(), user.getPassword())) {
            throw ServiceLayerException.forInvalidArgument("Invalid email or password");
        }
        return toUserDTO(user);
    }

    private UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getUserId(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getRole(),
                user.getPlan()
        );
    }

    private void validateUserId(Long userId) {
        if (userId == null || userId < 0) {
            throw ServiceLayerException.forInvalidArgument("Invalid user ID: " + userId);
        }
    }
}
