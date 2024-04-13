package com.tweet.cogent.tweet.app.controller;

import com.tweet.cogent.tweet.app.model.UserDto;
import com.tweet.cogent.tweet.app.entity.User;
import com.tweet.cogent.tweet.app.service.UserService;
import lombok.Data;
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

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.saveUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody UserDto userDto) {
        // Check if user exists by loginId or userName
        User userInDb = userService.findByLoginIdOrEmail(userDto.getUsernameOrEmail(), userDto.getUsernameOrEmail());
        if (userInDb != null) {
            if (userDto.getPassword().equals(userInDb.getPassword())) {
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
