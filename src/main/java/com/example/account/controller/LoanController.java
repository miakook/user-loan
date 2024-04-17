package com.example.account.controller;

import com.example.account.common.ErrorResponse;
import com.example.account.common.Response;
import com.example.account.common.exception.UserLoanException;
import com.example.account.dto.UserLoanDto;
import com.example.account.dto.UserLoanFormDto;
import com.example.account.service.LoanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {

    private final LoanService loanService;

    private static final Response INTERNAL_SERVER_ERROR = Response.errorResponse(500, new ErrorResponse("Internal Server Error"));

    @PostMapping
    public Response<Boolean> addLoan(@RequestHeader("userId") long userId,
                                         @Valid @RequestBody UserLoanFormDto form) {
        try {
            Boolean success = loanService.create(userId, form);
        } catch (UserLoanException e) {
            return Response.errorResponse(400, new ErrorResponse(e.getMessage()));
        } catch (Exception e) {
            log.error("Loan creation error!", e);
            return INTERNAL_SERVER_ERROR;
        }

        return Response.success(true);
    }

    @GetMapping
    public Response<List<UserLoanDto>> getLoanList(@RequestHeader("userId") long userId) {
        try {
            List<UserLoanDto> loan = loanService.getLoans(userId);
            return Response.success(loan);
        } catch (Exception e) {
            return INTERNAL_SERVER_ERROR;
        }
    }

    @GetMapping("/{loanId}")
    public Response<UserLoanDto> getLoanDetail(@RequestHeader("userId") long userId,
                                         @PathVariable("loanId") long userLoanId) {
        try {
            UserLoanDto loan = loanService.getLoanDetail(userId, userLoanId);
            return Response.success(loan);
        } catch (UserLoanException e) {
            return Response.errorResponse(401, new ErrorResponse("Invalid access"));
        }
    }

    @DeleteMapping("/{loanId}")
    public Response<UserLoanDto> deleteLoan(@RequestHeader("userId") long userId,
                                         @PathVariable("loanId") long userLoanId) {
        loanService.delete(userId, userLoanId);
        return Response.success(true);

    }

}