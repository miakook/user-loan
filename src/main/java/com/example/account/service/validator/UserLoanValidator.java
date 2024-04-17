package com.example.account.service.validator;

import com.example.account.common.exception.UserLoanException;
import com.example.account.dto.UserLoanFormDto;
import com.example.account.entity.Loan;
import com.example.account.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Component
public class UserLoanValidator {

    private final LoanRepository loanRepository;

    public void validate(UserLoanFormDto form) {
        validateInput(form);
        validateLoanType(form);
        validateRepayment(form);

    }

    private void validateInput(UserLoanFormDto form) {
        if (isNull(form)
                || isNull(form.getLoanTypeId())
                || isNull(form.getInterestRate())
                || isNull(form.getStartedAt())
                || isNull(form.getRepaymentDate())
                || isNull(form.getMaturity())
                || isNull(form.getTotalAmount())
                || isNull(form.getTotalRepayment())) {
            throw new UserLoanException("Invalid input");
        }
    }

    private void validateLoanType(UserLoanFormDto form) {
        Optional<Loan> optionalLoan = loanRepository.findById(form.getLoanTypeId());
        if (optionalLoan.isEmpty()) {
            throw new UserLoanException("Invalid Loan Type");
        }

        Loan loan = optionalLoan.get();
        if (form.getTotalAmount() > loan.getLimitAmount()) {
            throw new UserLoanException("Total amount cannot exceed " + loan.getLimitAmount());
        }
        BigDecimal baseRate = loan.getBaseRate();
        BigDecimal maxRate = baseRate.add(loan.getMaxSpread());
        if (form.getInterestRate().compareTo(baseRate) < 0) {
            throw new UserLoanException("Interest rate cannot be under " + baseRate);
        } else if (form.getInterestRate().compareTo(maxRate) > 0) {
            throw new UserLoanException("Interest rate cannot exceed " + maxRate);
        }

    }

    private void validateRepayment(UserLoanFormDto form) {
        if (form.getRepaymentDate() <=0 || form.getRepaymentDate() > 30) {
            throw new UserLoanException("Please select repayment date between 1~30");
        }
        if (form.getTotalRepayment() > form.getTotalAmount()) {
            throw new UserLoanException("Total repayment cannot be bigger than total amount");
        }
    }
}
