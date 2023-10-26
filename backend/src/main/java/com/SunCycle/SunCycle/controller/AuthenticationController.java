package com.SunCycle.SunCycle.controller;

import com.SunCycle.SunCycle.dto.LoginResponseDTO;
import com.SunCycle.SunCycle.dto.Status;
import com.SunCycle.SunCycle.model.User;
import com.SunCycle.SunCycle.repository.UserRepository;
import com.SunCycle.SunCycle.service.AuthenticationService;
import com.SunCycle.SunCycle.service.TokenService;
import com.SunCycle.SunCycle.service.UpdateUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private UpdateUserInfoService updateUserInfoService;

    // Create a new user
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user) {

        LoginResponseDTO response = authenticationService.registerUser(user.getUsername(), user.getPassword());

        if (response.getStatus() == Status.ALREADY_EXISTS){
            return new ResponseEntity<String>("Email already exist", HttpStatusCode.valueOf(409));
        }

        if (response.getStatus() == Status.ERROR) {
            return new ResponseEntity<String>("Authentication failed", HttpStatusCode.valueOf(401));
        }

        return ResponseEntity.ok(response);

    }

    // User login
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        LoginResponseDTO response = authenticationService.loginUser(user.getUsername(), user.getPassword());

        if (response.getStatus() == Status.NOT_FOUND) {
            return new ResponseEntity<String>("User not found", HttpStatusCode.valueOf(401));
        }

        if (response.getStatus() == Status.ERROR) {
            return new ResponseEntity<String>("Authentication failed", HttpStatusCode.valueOf(401));
        }

        return ResponseEntity.ok(response);


    }

    // Update user details
    @PutMapping("/{id}")
    public LoginResponseDTO updateUser(@PathVariable int id, @RequestBody User updateUserRequest) {

        return updateUserInfoService.updateUserById(id, updateUserRequest.getEmail(), updateUserRequest.getPassword());
    }

}
