package com.example.account.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
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
    private LocalDate startedAt;

    @Column(nullable = false)
    private LocalDate maturity;

    @Column(nullable = false)
    private Integer repaymentDate;

    @Column(nullable = false)
    private Long totalAmount;

    @Column(nullable = false)
    private Long totalRepayment;

}
