package com.example.account.repository;

import com.example.account.entity.UserLoan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoanRepository extends JpaRepository<UserLoan, Long> {
}
