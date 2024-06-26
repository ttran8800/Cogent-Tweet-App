package com.tweet.cogent.tweet.app.service;

import com.tweet.cogent.tweet.app.entity.Tweet;
import com.tweet.cogent.tweet.app.entity.User;
import com.tweet.cogent.tweet.app.payload.TweetRequestPayload;

import java.util.List;

public interface TweetService {
    Tweet createTweet (User user, TweetRequestPayload tweet);

    User createTweetReply(TweetRequestPayload tweet);

    Tweet getTweetById (Long tweetId);

    List<Tweet> getAllTweet();

    List<Tweet> getAllTweetReplies(Long parentId);
    void deleteTweet(Long tweetId);

}
