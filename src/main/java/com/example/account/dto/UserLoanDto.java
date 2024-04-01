package com.example.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class UserLoanDto {

    private Long loanId;
    private String bank;
    private String name;
    private String customName;
    private BigDecimal interestRate;

}
