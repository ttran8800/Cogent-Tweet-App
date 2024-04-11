package com.tweet.cogent.tweet.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, name = "first_name")
    private String firstName;


    @Column(nullable = false, name = "last_name")
    private String lastName;


    @Column(nullable = false, unique = true)
    private String email;


    @Column(nullable = false, name = "login_id", unique = true)
    private String loginId;


    @Column(nullable = false)
    private String password;


    @Column(nullable = false, name = "contact_number")
    private String contactNumber;


    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "id"
            )
    )
    private Set<Role> roles;
}
