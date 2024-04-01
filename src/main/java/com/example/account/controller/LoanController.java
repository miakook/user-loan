package com.example.account.controller;

import com.example.account.common.ErrorResponse;
import com.example.account.common.Response;
import com.example.account.dto.LoanFormDto;
import com.example.account.dto.UserLoanDto;
import com.example.account.service.LoanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {

    private final LoanService loanService;

    @PostMapping
    public Response<Boolean> addLoan(@RequestHeader("userId") long userId,
                                         @Valid @RequestBody LoanFormDto form) {
        Boolean success = loanService.create(userId, form);

        return BooleanUtils.isTrue(success) ? Response.success(true)
                : Response.errorResponse(400, new ErrorResponse("Bad request: invalid input"));
    }

    @GetMapping
    public Response<List<UserLoanDto>> getLoanList(@RequestHeader("userId") long userId) {
        try {
            List<UserLoanDto> loan = loanService.getLoans(userId);
            return Response.success(loan);
        } catch (Exception e) {
            return Response.errorResponse(500, new ErrorResponse("internal server error"));
        }
    }

    @GetMapping("/{loanId}")
    public Response<UserLoanDto> getLoanDetail(@RequestHeader("userId") long userId,
                                         @PathVariable("loanId") long loanId) {
        try {
            UserLoanDto loan = loanService.getLoanDetail(userId, loanId);
            return Response.success(loan);
        } catch (Exception e) {
            return Response.errorResponse(500, new ErrorResponse("internal server error"));
        }
    }

    @DeleteMapping("/{loanId}")
    public Response<UserLoanDto> deleteLoan(@RequestHeader("userId") long userId,
                                         @PathVariable("loanId") long loanId) {
        Boolean success = loanService.delete(userId, loanId);

        return BooleanUtils.isTrue(success) ? Response.success(true)
                : Response.errorResponse(400, new ErrorResponse("Bad request: invalid input"));

    }

}