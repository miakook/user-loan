package com.example.account.service;

import com.example.account.dto.LoanFormDto;
import com.example.account.dto.UserLoanDto;
import com.example.account.dto.converter.UserLoanDtoConverter;
import com.example.account.entity.UserLoan;
import com.example.account.entity.UserLoanExtension;
import com.example.account.repository.UserLoanExtensionRepository;
import com.example.account.repository.UserLoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@Service
public class LoanService {

    private final UserLoanRepository userLoanRepository;
    private final UserLoanExtensionRepository extensionRepository;
    private final UserLoanDtoConverter converter;

    public Boolean create(long userId, UserLoanFormDto form) {
        if (!isValid(form)) {
            return Boolean.FALSE;
        }

        UserLoan loan = new UserLoan();
        loan.setUserId(userId);
        loan.setLoanId(form.getLoanTypeId());
        loan.setInterestRate(form.getInterestRate());
        loan.setStartedAt(form.getStartedAt());
        loan.setRepaymentDate(form.getRepaymentDate());
        loan.setMaturity(form.getMaturity());
        loan.setTotalAmount(form.getTotalAmount());
        loan.setTotalRepayment(form.getTotalRepayment());
        userLoanRepository.save(loan);
        return Boolean.TRUE;
    }

    public List<UserLoanDto> getLoans(long userId) {
        List<UserLoan> loans = userLoanRepository.findByUserId(userId);
        return loans.stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public UserLoanDto getLoanDetail(long userId, long userLoanId) throws Exception {
        Optional<UserLoan> loanOptional = userLoanRepository.findById(userLoanId);
        if (!loanOptional.isPresent()) {
            throw new Exception("loan not found");
        }
        if (loanOptional.get().getUserId() != userId) {
            throw new Exception("userId of userLoan does not match");
        }

        UserLoan loan = loanOptional.get();
        Optional<UserLoanExtension> extensionOptional = extensionRepository.findByUserLoanId(loan.getUserLoanId());
        return extensionOptional.isPresent() ?
                converter.convertDetail(loan, extensionOptional.get())
                : converter.convert(loan);
    }

    public Boolean delete(long userId, long userLoanId) {
        Optional<UserLoan> loanOptional = userLoanRepository.findById(userLoanId);
        if (loanOptional.isPresent()) {
            UserLoan loan = loanOptional.get();
            if (loan.getUserId() == userId) {
                userLoanRepository.delete(loan);
            }
        }
        return Boolean.TRUE;
    }

    private boolean isValid(UserLoanFormDto form) {
        return nonNull(form)
                && nonNull(form.getLoanTypeId())
                && nonNull(form.getInterestRate())
                && nonNull(form.getStartedAt())
                && nonNull(form.getRepaymentDate())
                && nonNull(form.getMaturity())
                && nonNull(form.getTotalAmount())
                && nonNull(form.getTotalRepayment());
    }

}
