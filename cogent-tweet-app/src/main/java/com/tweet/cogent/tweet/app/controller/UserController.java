package com.tweet.cogent.tweet.app.controller;

import com.tweet.cogent.tweet.app.entity.User;
import com.tweet.cogent.tweet.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/tweets")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> createUser(User user) {
        User newUser = userService.saveUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity<String> userLogin(User user) {
        if (userService.isUserExist(user.getLoginId())) {
            User userInDb = userService.findByLoginId(user.getLoginId());
            if (user.getPassword().equals(userInDb.getPassword())) {
                return new ResponseEntity<>("login success", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("wrong password", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/v1.0/tweets/users/all")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> userList = userService.getAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}
