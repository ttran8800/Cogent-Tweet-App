package com.tweet.cogent.tweet.app.repository;

import com.tweet.cogent.tweet.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Boolean findByLoginId(String longId);

    Boolean exitsByEmail(String email);
}
