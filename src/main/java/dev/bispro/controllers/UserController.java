// dev.bispro.controllers.UserController.java
package dev.bispro.controllers;

import dev.bispro.domain.User;
import dev.bispro.dtos.UserDTO;
import dev.bispro.dtos.UserRegisterDTO;
import dev.bispro.dtos.UserLoginDTO;
import dev.bispro.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable User.UserId userId) {
        return ResponseEntity.ok(userService.findByUserId(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable User.UserId userId, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUser(userId, userDTO));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable User.UserId userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody UserLoginDTO loginUserDTO) {
        return ResponseEntity.ok(userService.login(loginUserDTO));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signupUser(@RequestBody UserRegisterDTO registerUserDTO) {
        return ResponseEntity.ok(userService.signup(registerUserDTO));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
