package com.SunCycle.SunCycle.controller;

import com.SunCycle.SunCycle.dto.LoginResponseDTO;
import com.SunCycle.SunCycle.model.User;
import com.SunCycle.SunCycle.repository.UserRepository;
import com.SunCycle.SunCycle.service.AuthenticationService;
import com.SunCycle.SunCycle.service.TokenService;
import com.SunCycle.SunCycle.service.UpdateUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
// @CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UpdateUserInfoService updateUserInfoService;

    // Create a new user
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user) {

        // Check if email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("email already exists");
        }

        User registeredUser = authenticationService.registerUser(user.getUsername(), user.getPassword());
        return ResponseEntity.ok(registeredUser);

    }

    // User login
    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody User user) {

        return authenticationService.loginUser(user.getUsername(), user.getPassword());
    }

    // Update user details
    @PutMapping("/{id}")
    public LoginResponseDTO updateUser(@PathVariable int id, @RequestBody User updateUserRequest) {

        return updateUserInfoService.updateUserById(id, updateUserRequest.getEmail(), updateUserRequest.getPassword());
    }

}
