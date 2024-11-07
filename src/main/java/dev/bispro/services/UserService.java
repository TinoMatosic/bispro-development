package dev.bispro.services;

import dev.bispro.domain.User;
import dev.bispro.dtos.UserDTO;
import dev.bispro.dtos.UserLoginDTO;
import dev.bispro.dtos.UserRegisterDTO;

import java.util.List;

public interface UserService {

    UserDTO findByUserId(Long userId);
    UserDTO updateUser(Long userId, UserDTO user);
    void deleteUser(Long userId);
    List<UserDTO> getAllUsers();
    UserDTO signup(UserRegisterDTO user);
    UserDTO login(UserLoginDTO loginUserDTO);

}
