package com.example.account.entity;

import jakarta.persistence.*;

@Entity
public class UserLoanExtension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userLoanExtensionId;

    @Column(nullable = false)
    private Long userLoanId;

    private String customName;

    private Integer remainingDate;

    private Integer remainingAmount;

}
