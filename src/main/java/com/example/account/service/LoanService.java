package com.example.account.service;

import com.example.account.dto.LoanFormDto;
import com.example.account.dto.UserLoanDto;
import com.example.account.entity.UserLoan;
import com.example.account.repository.UserLoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@Service
public class LoanService {

    private final UserLoanRepository userLoanRepository;

    public Boolean create(long userId, LoanFormDto form) {
        if (!isValid(form)) {
            return Boolean.FALSE;
        }

        UserLoan loan = new UserLoan();
        loan.setUserId(userId);
        loan.setLoanId(form.getLoanId());
        loan.setInterestRate(form.getInterestRate());
        loan.setStartedAt(form.getStartedAt());
        loan.setRepaymentDate(form.getRepaymentDate());
        loan.setTerm(form.getTerm());
        loan.setTotalAmount(form.getTotalAmount());
        loan.setTotalRepayment(form.getTotalRepayment());
        userLoanRepository.save(loan);
        return Boolean.TRUE;
    }

    public List<UserLoanDto> getLoans(long userId) {
        return null;
    }

    public UserLoanDto getLoanDetail(long userId, long loanId) {
        return null;
    }

    public Boolean delete(long userId, long loanId) {
        return null;
    }

    private boolean isValid(LoanFormDto form) {
        return nonNull(form)
                && nonNull(form.getLoanId())
                && nonNull(form.getInterestRate())
                && nonNull(form.getStartedAt())
                && nonNull(form.getRepaymentDate())
                && nonNull(form.getTerm())
                && nonNull(form.getTotalAmount())
                && nonNull(form.getTotalRepayment());
    }

}
