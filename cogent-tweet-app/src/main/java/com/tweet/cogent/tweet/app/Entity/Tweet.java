package com.tweet.cogent.tweet.app.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany (
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "tweet_tags",
            joinColumns = @JoinColumn(name = "tweet_id", referencedColumnName = "tweetId"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "tagId")
    )
    Set<Tag> tagSet = new HashSet<>();
}