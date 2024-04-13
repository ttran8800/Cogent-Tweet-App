package com.tweet.cogent.tweet.app.service;

import com.tweet.cogent.tweet.app.payload.LoginDTO;
import com.tweet.cogent.tweet.app.payload.LoginResponse;
import com.tweet.cogent.tweet.app.payload.RegisterDTO;
import com.tweet.cogent.tweet.app.payload.RegisterResponse;

public interface AuthenticationService {
    LoginResponse login(LoginDTO loginDTO);
    RegisterResponse register(RegisterDTO registerDTO);
}
