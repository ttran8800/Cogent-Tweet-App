package com.tweet.cogent.tweet.app.controller;

import com.tweet.cogent.tweet.app.entity.User;
import com.tweet.cogent.tweet.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1.0/tweets")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/api/v1.0/tweets/users/all")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> userList = userService.getAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}
