package com.SunCycle.SunCycle.service;

import com.SunCycle.SunCycle.Model.LoginResponseDTO;
import com.SunCycle.SunCycle.Model.User;
import com.SunCycle.SunCycle.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthenticationService  {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    public User registerUser(String username, String password){
        System.out.println("registering user");

        String encodedPassword = "{bcrypt}"+passwordEncoder.encode(password);

        return userRepository.save(new User(username, encodedPassword));
    }

    public LoginResponseDTO loginUser(String username, String password){
        System.out.println("logging in");

        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            System.out.println(auth == null);
            String token = tokenService.generateJwt(auth);

            return new LoginResponseDTO(userRepository.findByEmail(username).get(), token);

        } catch(AuthenticationException e){
            System.out.println(e);
            return new LoginResponseDTO(null, "");
        }
    }

}
