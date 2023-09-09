package com.SunCycle.SunCycle.Controller;

import com.SunCycle.SunCycle.Model.LoginResponseDTO;
import com.SunCycle.SunCycle.Model.User;
import com.SunCycle.SunCycle.Repository.UserRepository;
import com.SunCycle.SunCycle.service.AuthenticationService;
import com.SunCycle.SunCycle.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
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
    @PutMapping("/{id}/update")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User updateUserRequest) {
        // Fetch the user by ID
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            return ResponseEntity.badRequest().body(null);
        }

        User user = userOptional.get();

        // Update the email and password
        user.setEmail(updateUserRequest.getEmail());
        user.setPassword(updateUserRequest.getPassword()); // Remember to hash the password before saving

        // Save the updated user
        userRepository.save(user);

        return ResponseEntity.ok(user);
    }

}
