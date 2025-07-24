package com.project.todolist.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Set;

//@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "mobileNo"),
})
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 10)
    private String mobileNo;

    @NotBlank
    @Size(max = 120)
    private String password;

    private Set<Role> roles;
}
