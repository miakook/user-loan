package com.example.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class UserLoanFormDto {

    private Long loanTypeId;

    private BigDecimal interestRate;

    private LocalDate startedAt;

    private LocalDate maturity;

    private Integer repaymentDate;

    private Long totalAmount;

    private Long totalRepayment;

    private String customName;


}
