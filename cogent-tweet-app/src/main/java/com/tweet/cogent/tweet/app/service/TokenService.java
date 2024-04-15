package com.tweet.cogent.tweet.app.service;

import org.springframework.security.core.Authentication;

public interface TokenService {

    String generateJwt(Authentication authentication);
}
