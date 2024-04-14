package com.tweet.cogent.tweet.app.controller;

import com.tweet.cogent.tweet.app.payload.LoginDTO;
import com.tweet.cogent.tweet.app.payload.LoginResponse;
import com.tweet.cogent.tweet.app.payload.RegisterDTO;
import com.tweet.cogent.tweet.app.payload.RegisterResponse;
import com.tweet.cogent.tweet.app.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/api/v1.0/tweets")
public class AuthenticationController {

    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> login(@RequestBody RegisterDTO registerDTO) {
        try {
            RegisterResponse response = authenticationService.register(registerDTO);
            HttpStatus status = HttpStatus.CREATED; // Default successful status

            switch (response.getErrorType()) {
                case "LoginId":
                case "Email":
                    status = HttpStatus.CONFLICT;
                    break;
                case "Exception":
                    status = HttpStatus.INTERNAL_SERVER_ERROR;
                    break;
            }

            return new ResponseEntity<>(response, status);
        } catch (RuntimeException ex) {
            logger.error("Registration error: {}", ex.getMessage(), ex);
            return ResponseEntity.badRequest().body(new RegisterResponse(true, "Exception", "Error during registration: " + ex.getMessage(), null));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO loginDTO) {
        try {
            LoginResponse response = authenticationService.login(loginDTO);
            if (response.isError()) {
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }
            return ResponseEntity.ok(response);
        } catch (AuthenticationException ex) {
            logger.error("Login failed for user: {}", loginDTO.getUsernameOrEmail(), ex);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse(true, "Authentication failed: Invalid username or password", null));
        } catch (Exception ex) {
            logger.error("Login error: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new LoginResponse(true, "Error during login: " + ex.getMessage(), null));
        }
    }

}
