package com.project.todolist.model;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "Roles")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;
}
