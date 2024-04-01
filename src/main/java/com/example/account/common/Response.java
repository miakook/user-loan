package com.example.account.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Response<T> {

    private int status;

    private T body;

    private ErrorResponse error;

    public static <T> Response success(T body) {
        return Response.builder()
                .status(200)
                .body(body)
                .build();
    }

    public static Response errorResponse(int status, ErrorResponse error) {
        return Response.builder()
                .status(status)
                .error(error)
                .build();
    }

}
