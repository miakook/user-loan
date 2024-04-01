package com.example.account.controller

import com.example.account.dto.UserFormDto
import com.example.account.service.UserService
import spock.lang.Specification

import java.time.LocalDate

class UserControllerTest extends Specification {

//    UserController sut
//
//    UserService userService
//
//    void setup(){
//        userService = Mock()
//        sut = new UserController(userService)
//    }
//
//    def "sign up new user"(){
//        given:
//        def signUpUser = new UserFormDto("user", new LocalDate(2000, 1, 1))
//
//        when:
//        sut.signup(signUpUser)
//
//        then:
//        1 * userService.signup(signUpUser)
//    }
//
//    def "invalid user"(){
//        when:
//        sut.signup(signUpUser)
//
//        then:
//        0 * userService.signup(signUpUser)
//
//        where:
//        signUpUser << [
//                null,
//                new UserFormDto(null, new LocalDate(2000, 1, 1)),
//                new UserFormDto("", new LocalDate(2000, 1, 1)),
//                new UserFormDto("user", null)
//        ]
//    }
}
