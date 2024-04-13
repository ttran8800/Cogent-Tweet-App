package com.tweet.cogent.tweet.app.service;

import com.tweet.cogent.tweet.app.entity.User;

import java.util.List;

public interface UserService {
    User findByEmail(String email);

    User findByLoginId(String loginId);
    boolean isUserExistByLoginId(String loginId);
    boolean isUserExistByEmail(String email);
    User saveUser(User user);
    void deleteUser(Long id);

    List<User> getAllUsers();
}
