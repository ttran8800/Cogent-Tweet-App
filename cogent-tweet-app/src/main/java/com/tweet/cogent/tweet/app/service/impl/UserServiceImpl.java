package com.tweet.cogent.tweet.app.service.impl;

import com.tweet.cogent.tweet.app.entity.Role;
import com.tweet.cogent.tweet.app.entity.Tweet;
import com.tweet.cogent.tweet.app.entity.User;
import com.tweet.cogent.tweet.app.repository.RoleRepository;
import com.tweet.cogent.tweet.app.repository.UserRepository;
import com.tweet.cogent.tweet.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }

    @Override
    public User findByLoginId(String loginId) {
        return userRepository.findByLoginId(loginId)
                .orElseThrow(()-> new RuntimeException("User not found"));
    }

    @Override
    public boolean isUserExistByLoginId(String loginId) {
        return userRepository.existsByLoginId(loginId);
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User saveUser(User user) {
        Role role = roleRepository.findByName("USER").orElseThrow(() -> new RuntimeException("role not found"));
        user.getRoles().add(role);
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

    @Override
    public User updateUser(User user) {
        User updateUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        updateUser.setContactNumber(user.getContactNumber());
        updateUser.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(updateUser);
        return updateUser;
    }

    @Override
    public List<Tweet> getAllUserRecentTweet() {
        List<User> allUser = userRepository.findAll();
        return allUser.stream()
                .filter(user -> user.getTweets() != null && !user.getTweets().isEmpty())
                .map(user -> user.getTweets().stream()
                        .max(Comparator.comparing(Tweet::getDate))
                        .orElse(null))
                .toList();
    }
}
