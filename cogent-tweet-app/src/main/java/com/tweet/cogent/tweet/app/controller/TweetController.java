package com.tweet.cogent.tweet.app.controller;

import com.tweet.cogent.tweet.app.entity.Tweet;
import com.tweet.cogent.tweet.app.entity.User;
import com.tweet.cogent.tweet.app.payload.TweetRequestPayload;
import com.tweet.cogent.tweet.app.service.TweetService;
import com.tweet.cogent.tweet.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

    Logger logger = LoggerFactory.getLogger(getClass());


    @GetMapping("/all")
    public ResponseEntity<List<Tweet>> getAllTweet() {
        List<Tweet> tweetList = tweetService.getAllTweet();
        return new ResponseEntity<>(tweetList, HttpStatus.OK);
    }

    @GetMapping("/allReplies/{parentId}")
    public ResponseEntity<?> getAllTweetReplies(@PathVariable("parentId") Long id, Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }try {
            List<Tweet> tweetReplyList = tweetService.getAllTweetReplies(id);
            return new ResponseEntity<>(tweetReplyList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error creating tweet: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<Tweet>> getAllTweetFromUser(@PathVariable("username") String username) {
        User user = userService.findByLoginId(username);
        List<Tweet> userTweets = user.getTweets().stream().toList();
        return new ResponseEntity<>(userTweets, HttpStatus.OK);
    }

    @PostMapping("/{username}/add")
    public ResponseEntity<?> addTweet(@PathVariable("username") String username, @RequestBody TweetRequestPayload tweet, Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        try {
            User user = userService.findByLoginId(username);
            Tweet newTweet = tweetService.createTweet(user, tweet);
            user = userService.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating tweet: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/{username}/reply/{id}")
    public ResponseEntity<?> replyTweet(@PathVariable("username") String username, @RequestBody TweetRequestPayload tweet, Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        try {
            User updatedUser = tweetService.createTweetReply(tweet);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error creating tweet: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
