package com.tweet.cogent.tweet.app.repository;

import com.tweet.cogent.tweet.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Long, User> {
    Optional<User> findByEmail(String email);
    Optional<User> findByLoginId(String loginId);
    Optional<User> findByLoginId(String loginId, String email);
    Boolean existsByLoginId(String loginId);
    Boolean exitsByEmail(String email);
}
