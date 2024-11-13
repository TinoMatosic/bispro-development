package dev.bispro.services;

import dev.bispro.domain.User;
import dev.bispro.dtos.UserDTO;
import dev.bispro.dtos.UserLoginDTO;
import dev.bispro.dtos.UserRegisterDTO;

import java.util.List;

public interface UserService {

    UserDTO findByUserId(User.UserId userId);
    UserDTO updateUser(User.UserId userId, UserDTO user);
    void deleteUser(User.UserId userId);
    List<UserDTO> getAllUsers();
    UserDTO signup(UserRegisterDTO user);
    UserDTO login(UserLoginDTO loginUserDTO);

}
