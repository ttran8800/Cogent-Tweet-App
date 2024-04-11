package com.tweet.cogent.tweet.app.service;

import com.tweet.cogent.tweet.app.entity.User;

import java.util.List;

public interface UserService {
    User findByEmail(String email);
    boolean findByLoginId(String id);
    User saveUser(User user);
    void deleteUser(Long id);

    List<User> getAllUsers();
}
