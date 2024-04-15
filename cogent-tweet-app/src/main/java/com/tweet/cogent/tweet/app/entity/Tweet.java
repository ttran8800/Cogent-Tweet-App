package com.tweet.cogent.tweet.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tweets")
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tweetId;
    @Column(nullable = false)
    private String nameWithHandle;
    @Column(nullable = false)
    private Date date;
    @Column(nullable = false, length = 144)
    private String message;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "user_Id",
            nullable = false
    )
    @JsonBackReference
    private User user;

    @ManyToMany (
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "tweet_tags",
            joinColumns = @JoinColumn(name = "tweet_id", referencedColumnName = "tweetId"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "tagId")
    )
    Set<Tag> tagSet = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_tweet_id")
    private Tweet parentTweet;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tweet tweet = (Tweet) o;
        return Objects.equals(tweetId, tweet.tweetId) &&
                Objects.equals(nameWithHandle, tweet.nameWithHandle) &&
                Objects.equals(date, tweet.date) &&
                Objects.equals(message, tweet.message);
        // Note: Excluding user and parentTweet to prevent recursion
    }

    @Override
    public int hashCode() {
        return Objects.hash(tweetId, nameWithHandle, date, message);
        // Note: Excluding user and parentTweet to prevent recursion
    }
}