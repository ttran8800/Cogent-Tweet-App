package com.tweet.cogent.tweet.app.repository;

import com.tweet.cogent.tweet.app.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
