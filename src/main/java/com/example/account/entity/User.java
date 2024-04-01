package com.example.account.entity;

import jakarta.persistence.*;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate birthDate;

}
