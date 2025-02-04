package com.banking.bank_web_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.banking.bank_web_app.model.Account;
import com.banking.bank_web_app.repository.AccountRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/accounts")
    public List<Account> getAccountsByUserId(@RequestParam(("userId")) Long userId) {
        return accountRepository.findByUserId(userId);
    }
}