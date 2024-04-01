package com.example.account.service;

import com.example.account.dto.UserFormDto;
import com.example.account.entity.User;
import com.example.account.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;

    public Boolean signup(UserFormDto form){
        if (!isValid(form)) {
            return Boolean.FALSE;
        }

        User user = new User();
        user.setName(form.getName());
        user.setBirthDate(form.getBirthDate());
        repository.save(user);
        return Boolean.TRUE;
    }

    private boolean isValid(UserFormDto form) {
        return nonNull(form)
                && StringUtils.isNotEmpty(form.getName())
                && nonNull(form.getBirthDate());
    }

}
