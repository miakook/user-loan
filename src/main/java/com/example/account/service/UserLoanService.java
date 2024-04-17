package com.example.account.service;

import com.example.account.common.exception.UserLoanException;
import com.example.account.dto.UserLoanFormDto;
import com.example.account.dto.UserLoanDto;
import com.example.account.dto.converter.UserLoanDtoConverter;
import com.example.account.entity.Loan;
import com.example.account.entity.UserLoan;
import com.example.account.entity.UserLoanExtension;
import com.example.account.repository.LoanRepository;
import com.example.account.repository.UserLoanExtensionRepository;
import com.example.account.repository.UserLoanRepository;
import com.example.account.service.validator.UserLoanValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserLoanService {

    private final UserLoanRepository userLoanRepository;
    private final LoanRepository loanRepository;
    private final UserLoanValidator userLoanValidator;
    private final UserLoanExtensionRepository extensionRepository;
    private final UserLoanDtoConverter converter;

    public Boolean create(long userId, UserLoanFormDto form) {
        userLoanValidator.validate(form);

        Loan loan = getLoan(form);
        userLoanValidator.validateInput(form, loan);

        UserLoan userLoan = new UserLoan();
        userLoan.setUserId(userId);
        userLoan.setLoanId(form.getLoanTypeId());
        userLoan.setInterestRate(form.getInterestRate());
        userLoan.setStartedAt(form.getStartedAt());
        userLoan.setRepaymentDate(form.getRepaymentDate());
        userLoan.setMaturity(form.getMaturity());
        userLoan.setTotalAmount(form.getTotalAmount());
        userLoan.setTotalRepayment(form.getTotalRepayment());
        userLoan = userLoanRepository.save(userLoan);

        try {
            UserLoanExtension extension = new UserLoanExtension();
            extension.setName(loan.getName());
            extension.setUserLoanId(userLoan.getUserLoanId());
            extension.setCustomName(form.getCustomName());
            extension.setTermToMaturity(calcTerm(form));
            extension.setRemainingAmount(caldRemainAmount(form));
            extensionRepository.save(extension);
        } catch (Exception e) {
            log.warn("extension save error", e);
        }

        return Boolean.TRUE;
    }

    public List<UserLoanDto> getLoans(long userId) {
        List<UserLoan> loans = userLoanRepository.findByUserId(userId);
        return loans.stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public UserLoanDto getLoanDetail(long userId, long userLoanId) {
        Optional<UserLoan> loanOptional = userLoanRepository.findById(userLoanId);
        if (!loanOptional.isPresent()) {
            throw new UserLoanException("loan not found");
        }
        if (loanOptional.get().getUserId() != userId) {
            throw new UserLoanException("userId of userLoan does not match");
        }

        UserLoan loan = loanOptional.get();
        Optional<UserLoanExtension> extensionOptional = extensionRepository.findByUserLoanId(loan.getUserLoanId());
        return extensionOptional.isPresent() ?
                converter.convertDetail(loan, extensionOptional.get())
                : converter.convert(loan);
    }

    public Boolean delete(long userId, long userLoanId) {
        Optional<UserLoan> loanOptional = userLoanRepository.findById(userLoanId);
        if (loanOptional.isEmpty()) {
            return Boolean.FALSE;
        }

        UserLoan loan = loanOptional.get();
        if (loan.getUserId() != userId) {
            return Boolean.FALSE;
        }

        userLoanRepository.delete(loan);
        Optional<UserLoanExtension> extension = extensionRepository.findByUserLoanId(loan.getUserLoanId());
        if (extension.isPresent()) {
            extensionRepository.delete(extension.get());
        }

        return Boolean.TRUE;
    }

    private Loan getLoan(UserLoanFormDto form) {
        Optional<Loan> optionalLoan = loanRepository.findById(form.getLoanTypeId());
        if (optionalLoan.isEmpty()) {
            throw new UserLoanException("Invalid Loan Type");
        }
        Loan loan = optionalLoan.get();
        return loan;
    }

    private long calcTerm(UserLoanFormDto form) {
        return ChronoUnit.DAYS.between(LocalDate.now(), form.getMaturity());
    }

    private long caldRemainAmount(UserLoanFormDto form) {
        return form.getTotalAmount() - form.getTotalRepayment();
    }

}
