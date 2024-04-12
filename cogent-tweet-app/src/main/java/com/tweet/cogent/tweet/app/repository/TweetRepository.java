package com.tweet.cogent.tweet.app.repository;

import com.tweet.cogent.tweet.app.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TweetRepository extends JpaRepository<Tweet, Long> {
}
