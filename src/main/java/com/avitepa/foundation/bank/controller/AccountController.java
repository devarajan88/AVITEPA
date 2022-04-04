package com.avitepa.foundation.bank.controller;

import com.avitepa.foundation.bank.model.Account;
import com.avitepa.foundation.bank.model.Customer;
import com.avitepa.foundation.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/accounts/add")
    public ResponseEntity addAccount(@RequestBody Account acc) {
        accountService.addAccount(acc);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @GetMapping("/accounts/all")
    public ResponseEntity getAllAccounts() {
        List<Account> allAccounts = accountService.getAllAccounts();
        return new ResponseEntity<>(allAccounts, HttpStatus.OK);
    }

    @GetMapping("/customers/all")
    public ResponseEntity getAllCustomers() {
        List<Customer> allCustomers = new ArrayList<>();
        return new ResponseEntity<>(allCustomers, HttpStatus.OK);
    }

    @PostMapping("/accounts/transfer")
    public ResponseEntity transferFunds(@RequestParam(name = "fromAccount") int from, @RequestParam(name = "toAccount") int to,
                                        @RequestParam(name = "amount") double amount) {
        String transferStatus =accountService.transferFunds(from, to, amount);
        return new ResponseEntity<>(transferStatus, HttpStatus.OK);
    }

    @GetMapping("/accounts/balance")
    public ResponseEntity getBalanceOf(@RequestParam(name = "accountNumber") int accountNumber) {
        Optional<Account> account = accountService.getBalanceOf(accountNumber);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}
