package com.tweet.cogent.tweet.app.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TweetRequestPayload {
    private Long tweetId;
    private String loginId;
    private String message;
    private Date date;
}
