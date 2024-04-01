package com.example.account.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class UserLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userLoanId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long loanId;

    @Column(nullable = false)
    private BigDecimal interestRate;

    @Column(nullable = false)
    private LocalDateTime startedAt;

    @Column(nullable = false)
    private Integer repaymentDate;

    @Column(nullable = false)
    private Integer term; // TODO in day? month?

    @Column(nullable = false)
    private Long totalAmount;

    @Column(nullable = false)
    private Long totalRepayment;

}
