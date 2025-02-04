package com.banking.bank_web_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.banking.bank_web_app.model.Transaction;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT COALESCE(MAX(t.id), 0) FROM Transaction t")
    Long findMaxId();
}