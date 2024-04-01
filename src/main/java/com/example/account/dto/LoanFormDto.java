package com.example.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class LoanFormDto {

    private Long loanId;

    private BigDecimal interestRate;

    private LocalDateTime startedAt;

    private Integer repaymentDate;

    private Integer term;

    private Long totalAmount;

    private Long totalRepayment;

}
