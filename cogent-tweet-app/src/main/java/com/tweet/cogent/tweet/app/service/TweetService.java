package com.tweet.cogent.tweet.app.service;

import com.tweet.cogent.tweet.app.entity.Tweet;
import com.tweet.cogent.tweet.app.entity.User;

import java.util.List;

public interface TweetService {
    Tweet createTweet (User user, Tweet tweet);

    Tweet getTweetById (Long tweetId);

    List<Tweet> getAllTweet();

    void deleteTweet(Long tweetId);

}
