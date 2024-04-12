package com.tweet.cogent.tweet.app.Loader;

import com.tweet.cogent.tweet.app.entity.Role;
import com.tweet.cogent.tweet.app.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RoleLoader implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        try {

            Role role = roleRepository.findByName("USER").orElseGet(() -> {
                Role newRole = new Role();
                newRole.setName("USER");
                return newRole;
            });

            roleRepository.save(role);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
