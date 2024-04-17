package com.example.account.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserLoanExtension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userLoanExtensionId;

    @Column(nullable = false, unique = true)
    private Long userLoanId;

    @Column(nullable = false)
    private String name;

    @Column
    private String customName;

    @Column(nullable = false)
    private Long termToMaturity;

    @Column(nullable = false)
    private Long remainingAmount;

}
