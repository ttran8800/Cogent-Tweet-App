package com.tweet.cogent.tweet.app.service.impl;

import com.tweet.cogent.tweet.app.entity.User;
import com.tweet.cogent.tweet.app.repository.UserRepository;
import com.tweet.cogent.tweet.app.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }

    @Override
    public boolean findByLoginId(String longId) {
        return userRepository.findByLoginId(longId);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
