package com.example.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class UserFormDto {

    private String name;

    private LocalDate birthDate;

}
