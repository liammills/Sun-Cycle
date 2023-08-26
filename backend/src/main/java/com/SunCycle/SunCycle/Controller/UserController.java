package com.SunCycle.SunCycle.Controller;

import com.SunCycle.SunCycle.Model.User;
import com.SunCycle.SunCycle.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    // Create a new user
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        // Check if the username already exists
        if (userRepository.findById(user.getId()).isPresent()) {
            return ResponseEntity.badRequest().body(null); // or return a custom error message
        }
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    // User login
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) {
        Optional<User> foundUser = userRepository.findById(user.getId());
        if (foundUser.isPresent() && foundUser.get().getPassword().equals(user.getPassword())) { // Assuming you're storing passwords in plain text for simplicity. In a real-world scenario, you'd use hashed passwords.
            return ResponseEntity.ok(foundUser.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // or return a custom error message
        }
    }

    // Update user details
    @PutMapping("/{id}/update")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        Optional<User> optionalUser = userRepository.findById(Math.toIntExact(id));
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEmail(userDetails.getEmail()); // Update username
            user.setPassword(userDetails.getPassword()); // Update password
            // Update other fields as necessary
            return ResponseEntity.ok(userRepository.save(user));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
