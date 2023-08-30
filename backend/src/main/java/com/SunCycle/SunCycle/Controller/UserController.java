package com.SunCycle.SunCycle.Controller;

import com.SunCycle.SunCycle.Model.User;
import com.SunCycle.SunCycle.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Create a new user
    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody User user) {

        // Check if email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already in use.");
        }

        // Save the new user
        userRepository.save(user);
        return ResponseEntity.ok("User created successfully.");

    }

    // User login
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) {
        Optional<User> foundUser = userRepository.findByEmail(user.getEmail());

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
