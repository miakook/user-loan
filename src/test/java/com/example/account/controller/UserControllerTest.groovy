package com.example.account.controller

import com.example.account.service.UserService
import spock.lang.Specification

class UserControllerTest extends Specification {

    UserController sut

    UserService userService

    void setup(){
        userService = Mock()
        sut = new UserController(userService)
    }

    def "sign up new user"(){
        given:
        def signUpUser

        when:
        def response = sut.signup(signUpUser)

        then:
        1 * userService.signup(signUpUser)
        response.getBody() == true
    }

    def "invalid user"(){
        given:
        def signUpUser

        when:
        def response = sut.signup(signUpUser)

        then:
        0 * userService.signup(signUpUser)
        response.getBody() == false
    }
}
