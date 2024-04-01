package com.example.account.common;

import lombok.Getter;

@Getter
public class Response<T> {

    private int status;

    private T body;

    private Error error;

}
