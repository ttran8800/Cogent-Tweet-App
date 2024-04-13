package com.tweet.cogent.tweet.app.payload;

import com.tweet.cogent.tweet.app.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterResponse {
    private boolean error;
    private String message;
    private User user;
}
