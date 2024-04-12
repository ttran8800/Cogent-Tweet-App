package com.tweet.cogent.tweet.app.repository;

import com.tweet.cogent.tweet.app.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Long, Role> {
    Optional<Role> findByRoleName(String name);

}
