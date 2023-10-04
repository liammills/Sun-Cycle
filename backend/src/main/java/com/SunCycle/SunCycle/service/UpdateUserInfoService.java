package com.SunCycle.SunCycle.service;

import com.SunCycle.SunCycle.model.LoginResponseDTO;
import com.SunCycle.SunCycle.model.User;
import com.SunCycle.SunCycle.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UpdateUserInfoService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public LoginResponseDTO updateUserById(Integer id, String newEmail, String newPassword) throws UsernameNotFoundException {
        System.out.println("in the process of updating");
        // Fetch the user by ID
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            return new LoginResponseDTO("this id does not exist");
        }

        if (userRepository.findByEmail(newEmail).isPresent()) {
            return new LoginResponseDTO("this email has aldready been used");
        }

        User user = userOptional.get();

        // Update the email and password
        user.setEmail(newEmail);
        user.setPassword("{bcrypt}"+encoder.encode(newPassword)); // Remember to hash the password before saving

        // Save the updated user
        userRepository.save(user);

        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(newEmail, newPassword)
            );
            System.out.println(auth == null);
            String token = tokenService.generateJwt(auth);

            return new LoginResponseDTO(user, token);

        } catch(AuthenticationException e){
            System.out.println(e);
            return new LoginResponseDTO("error");
        }
    }

}
