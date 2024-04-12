package com.tweet.cogent.tweet.app.controller;

import com.tweet.cogent.tweet.app.entity.Tweet;
import com.tweet.cogent.tweet.app.entity.User;
import com.tweet.cogent.tweet.app.service.TweetService;
import com.tweet.cogent.tweet.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1.0/tweets")
public class TweetController {

    @Autowired
    private TweetService tweetService;
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<Tweet>> getAllTweet() {
        List<Tweet> tweetList = tweetService.getAllTweet();
        return new ResponseEntity<>(tweetList, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<Tweet>> getAllTweetFromUser(@PathVariable("username") String username) {
        User user = userService.findByLoginId(username);
        List<Tweet> userTweets = user.getTweets().stream().toList();
        return new ResponseEntity<>(userTweets, HttpStatus.OK);
    }

    @PostMapping("/{username}/add")
    public ResponseEntity<Tweet> addTweet(@PathVariable("username") String username, @RequestBody Tweet tweet) {
        User user = userService.findByLoginId(username);
        Tweet newTweet = tweetService.createTweet(user, tweet);
        return new ResponseEntity<>(newTweet, HttpStatus.CREATED);
    }

}
