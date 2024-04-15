package com.tweet.cogent.tweet.app.service.impl;

import com.tweet.cogent.tweet.app.entity.Tag;
import com.tweet.cogent.tweet.app.entity.Tweet;
import com.tweet.cogent.tweet.app.entity.User;
import com.tweet.cogent.tweet.app.payload.TweetRequestPayload;
import com.tweet.cogent.tweet.app.repository.TweetRepository;
import com.tweet.cogent.tweet.app.repository.UserRepository;
import com.tweet.cogent.tweet.app.service.TweetService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TweetServiceImpl implements TweetService {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private UserRepository userRepository;

    //implement tag later
    @Transactional
    @Override
    public Tweet createTweet(User user, TweetRequestPayload tweet) {
        User existingUser = userRepository.findByLoginId(user.getLoginId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Tweet newTweet = new Tweet();

        newTweet.setDate(tweet.getDate());
        newTweet.setUser(user);
        newTweet.setMessage(tweet.getMessage());
        newTweet.setNameWithHandle(tweet.getLoginId());

        existingUser.getTweets().add(newTweet);
        tweetRepository.save(newTweet);
        return newTweet;
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
