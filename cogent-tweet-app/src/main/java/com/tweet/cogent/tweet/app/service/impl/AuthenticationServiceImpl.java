package com.tweet.cogent.tweet.app.service.impl;

import com.tweet.cogent.tweet.app.entity.Role;
import com.tweet.cogent.tweet.app.entity.User;
import com.tweet.cogent.tweet.app.payload.LoginDTO;
import com.tweet.cogent.tweet.app.payload.LoginResponse;
import com.tweet.cogent.tweet.app.payload.RegisterDTO;
import com.tweet.cogent.tweet.app.payload.RegisterResponse;
import com.tweet.cogent.tweet.app.repository.RoleRepository;
import com.tweet.cogent.tweet.app.service.AuthenticationService;
import com.tweet.cogent.tweet.app.service.TokenService;
import com.tweet.cogent.tweet.app.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    @Override
    public LoginResponse login(LoginDTO loginDTO) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword())
            );

            String token = tokenService.generateJwt(authentication);

            return new LoginResponse(false, "Login Success", token);
        } catch (AuthenticationException e) {
            return new LoginResponse(true, "Authentication Error", "");
        }
    }

    @Override
    public RegisterResponse register(RegisterDTO registerDTO) {
        if (userService.isUserExistByLoginId(registerDTO.getLoginId())) {
            throw new RuntimeException("loginId already in use!");
        }
        if (userService.isUserExistByEmail(registerDTO.getEmail())) {
            throw new RuntimeException("Email already in use!");
        }

        User user = new User();
        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setLoginId(registerDTO.getLoginId());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setContactNumber(registerDTO.getContactNumber());

        user.getRoles().add(roleRepository.findByName("USER").orElseThrow(() -> new RuntimeException("Role USER not found")));

        User savedUser = userService.saveUser(user);

        return new RegisterResponse(false, "Registered Successfully", savedUser);
    }
}
