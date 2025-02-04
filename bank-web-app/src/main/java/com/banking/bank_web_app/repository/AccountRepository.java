package com.banking.bank_web_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.banking.bank_web_app.model.Account;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByUserId(Long userId);
}