package com.example.account.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    @Column(nullable = false)
    private String bankCode;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal baseRate;

    @Column(nullable = false)
    private BigDecimal maxSpread = BigDecimal.ZERO;

    @Column(nullable = false)
    private Long limitAmount;

}
