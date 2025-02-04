package com.banking.bank_web_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.banking.bank_web_app.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}