package com.tweet.cogent.tweet.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;
    @Column(nullable = false)
    private String tagName;
    @Column(nullable = true, length = 50)
    private String message;

    @ManyToMany(
            fetch = FetchType.LAZY
    )
    Set<Tweet> tweetSet = new HashSet<>();
}
