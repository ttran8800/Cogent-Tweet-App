package com.tweet.cogent.tweet.app.controller;

import com.tweet.cogent.tweet.app.payload.LoginDTO;
import com.tweet.cogent.tweet.app.payload.LoginResponse;
import com.tweet.cogent.tweet.app.payload.RegisterDTO;
import com.tweet.cogent.tweet.app.payload.RegisterResponse;
import com.tweet.cogent.tweet.app.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1.0/tweets")
public class AuthenticationController {


    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> login(@RequestBody RegisterDTO registerDTO) {
        RegisterResponse response = authenticationService.register(registerDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO loginDTO) {
        LoginResponse response = authenticationService.login(loginDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
