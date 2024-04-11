package com.tweet.cogent.tweet.app.service;

import com.tweet.cogent.tweet.app.entity.Tweet;

import java.util.List;

public interface TweetService {
    Tweet creatTweet (Tweet tweet);

    Tweet getTweetById (Long tweetId);

    List<Tweet> getAllTweet();

    void deleteTweet(Long tweetId);

}
