package com.tweet.cogent.tweet.app.controller;

import com.tweet.cogent.tweet.app.entity.Tweet;
import com.tweet.cogent.tweet.app.entity.User;
import com.tweet.cogent.tweet.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1.0/tweets")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users/all")
    public ResponseEntity<List<Tweet>> getAllUser(Authentication authentication) {
        if (authentication != null) {
            List<User> userList = userService.getAllUsers();
            List<Tweet> allUserRecentTweet = userService.getAllUserRecentTweet();
            return new ResponseEntity<>(allUserRecentTweet, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/users/getUser")
    public ResponseEntity<User> getUser(Authentication authentication) {
        if (authentication != null) {
            String loginId = authentication.getName();
            User user = userService.findByLoginId(loginId);
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/users")
    public ResponseEntity<?> updateUser(Authentication authentication, @RequestBody User user) {
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        try {
            User updatedUser = userService.updateUser(user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
