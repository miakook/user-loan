package com.example.account.repository;

import com.example.account.entity.UserLoanExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLoanExtensionRepository extends JpaRepository<UserLoanExtension, Long> {

    Optional<UserLoanExtension> findByUserLoanId(Long userLoanId);

}
