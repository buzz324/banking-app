package com.banking.bank_web_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.banking.bank_web_app.model.Account;
import com.banking.bank_web_app.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TransferController {

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/transfer")
    public String transfer(@RequestBody TransferRequest transferRequest) {
        Optional<Account> fromAccountOpt = accountRepository.findById(transferRequest.getFromAccountId());
        Optional<Account> toAccountOpt = accountRepository.findById(transferRequest.getToAccountId());

        if (fromAccountOpt.isPresent() && toAccountOpt.isPresent()) {
            Account fromAccount = fromAccountOpt.get();
            Account toAccount = toAccountOpt.get();

            BigDecimal amount = transferRequest.getAmount();
            if (fromAccount.getBalance().compareTo(amount) >= 0) {
                fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
                toAccount.setBalance(toAccount.getBalance().add(amount));

                accountRepository.save(fromAccount);
                accountRepository.save(toAccount);

                return "Transfer successful";
            } else {
                return "Insufficient funds";
            }
        } else {
            return "Account not found";
        }
    }
}

class TransferRequest {
    private Long fromAccountId;
    private Long toAccountId;
    private BigDecimal amount;

    // Getters and setters
    public Long getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(Long fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Long toAccountId) {
        this.toAccountId = toAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}