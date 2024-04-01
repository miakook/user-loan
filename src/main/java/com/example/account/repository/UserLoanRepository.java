package com.example.account.repository;

import com.example.account.entity.UserLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;

public interface UserLoanRepository extends JpaRepository<UserLoan, Long> {

    List<UserLoan> findByUserId(Long userId);

}
