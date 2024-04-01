package com.example.account.dto.converter;

import com.example.account.dto.UserLoanDto;
import com.example.account.entity.UserLoan;
import com.example.account.entity.UserLoanExtension;
import org.springframework.stereotype.Component;

@Component
public class UserLoanDtoConverter {

    public UserLoanDto convert(UserLoan loan) {
        return UserLoanDto.builder()
                .userLoanId(loan.getUserLoanId())
                .totalAmount(loan.getTotalAmount())
                .repaymentDate(loan.getRepaymentDate())
                .interestRate(loan.getInterestRate())
                .build();
    }

    public UserLoanDto convertDetail(UserLoan loan, UserLoanExtension extension) {
        return UserLoanDto.builder()
                .userLoanId(loan.getUserLoanId())
                .name(extension.getName())
                .customName(extension.getCustomName())
                .totalAmount(loan.getTotalAmount())
                .repaymentDate(loan.getRepaymentDate())
                .remainingAmount(extension.getRemainingAmount())
                .interestRate(loan.getInterestRate())
                .build();
    }
}
