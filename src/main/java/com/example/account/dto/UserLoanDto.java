package com.example.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Getter
public class UserLoanDto {

    private Long userLoanId;

    private String name;

    private String customName;

    private Integer repaymentDate;

    private Long totalAmount;

    private Long remainingAmount;

    private BigDecimal interestRate;

}
