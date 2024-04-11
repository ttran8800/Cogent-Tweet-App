package com.tweet.cogent.tweet.app.service.Impl;

import com.tweet.cogent.tweet.app.entity.Tweet;
import com.tweet.cogent.tweet.app.repository.TweetRepository;
import com.tweet.cogent.tweet.app.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetServiceImpl implements TweetService {

    @Autowired
    private TweetRepository tweetRepository;
    @Override
    public Tweet creatTweet(Tweet tweet) {
        return tweetRepository.save(tweet);
    }

    @Override
    public Tweet getTweetById(Long tweetId) {
        return tweetRepository.findById(tweetId).orElseThrow(() -> new RuntimeException("Tweet not found"));
    }

    @Override
    public List<Tweet> getAllTweet() {
        return tweetRepository.findAll();
    }

    @Override
    public void deleteTweet(Long tweetId) {
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new RuntimeException("Tweet not found"));
        tweetRepository.deleteById(tweetId);
        //logging, delete later
        System.out.println("tweet successfully delete");
    }
}
