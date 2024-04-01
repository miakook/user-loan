package com.example.account.controller;

import com.example.account.common.ErrorResponse;
import com.example.account.common.Response;
import com.example.account.dto.UserFormDto;
import com.example.account.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public Response<Boolean> signup(@Valid @RequestBody UserFormDto form) {
        if (!isValid(form)) {
            return Response.errorResponse(400, new ErrorResponse("Bad request: invalid input"));
        }

        return Response.success(userService.signup(form));
    }

    private boolean isValid(UserFormDto form) {
        return nonNull(form)
                && StringUtils.isNotEmpty(form.getName())
                && nonNull(form.getBirthDate());
    }

}
