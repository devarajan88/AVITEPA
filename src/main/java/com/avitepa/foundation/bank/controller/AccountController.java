package com.avitepa.foundation.bank.controller;

import com.avitepa.foundation.bank.exceptionhandling.AccountServiceException;
import com.avitepa.foundation.bank.exceptionhandling.ResourceNotFoundException;
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
    public ResponseEntity addAccount(@RequestBody Account acc) throws ResourceNotFoundException, AccountServiceException {
        try{
            Account account = accountService.addAccount(acc);
            if (account == null) {
                throw new ResourceNotFoundException("Account not found");
            }
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Internal Server Exception while getting exception");
        }
    }

    @GetMapping("/accounts/all")
    public ResponseEntity<List<Account>> getAllAccounts() throws ResourceNotFoundException, AccountServiceException {
        try{
            List<Account> allAccounts = accountService.getAllAccounts();
            if(allAccounts.isEmpty()) {
                throw new ResourceNotFoundException("Accounts not found");
            }
            return new ResponseEntity<>(allAccounts, HttpStatus.OK);
        } catch (AccountServiceException e) {
            throw new AccountServiceException("Internal Server Exception while getting exception");
        }

    }

    @GetMapping("/customers/all")
    public ResponseEntity<List<Customer>> getAllCustomers() throws ResourceNotFoundException, AccountServiceException {
        try {
            List<Customer> allCustomers = accountService.getAllCustomers();
            if(allCustomers.isEmpty()) {
                throw new ResourceNotFoundException("Accounts not found");
            }
            return new ResponseEntity<>(allCustomers, HttpStatus.OK);
        } catch (AccountServiceException e) {
            throw new AccountServiceException("Internal Server Exception while getting exception");
        }
    }

    @PostMapping("/accounts/transfer")
    public ResponseEntity<String> transferFunds(@RequestParam(name = "fromAccount") int from, @RequestParam(name = "toAccount") int to,
                                        @RequestParam(name = "amount") double amount) throws AccountServiceException {
        try {
            String transferStatus =accountService.transferFunds(from, to, amount);
            return new ResponseEntity<>(transferStatus, HttpStatus.OK);
        } catch (AccountServiceException e) {
            throw new AccountServiceException("Internal Server Exception while getting exception");
        }
    }

    @GetMapping("/accounts/balance")
    public ResponseEntity<Optional<Account>> getBalanceOf(@RequestParam(name = "accountNumber") int accountNumber)
            throws ResourceNotFoundException, AccountServiceException {
        try {
            Optional<Account> account = accountService.getBalanceOf(accountNumber);
            if(account.isEmpty()) {
                throw new ResourceNotFoundException("Accounts not found");
            }
            return new ResponseEntity<>(account, HttpStatus.OK);
        } catch (AccountServiceException e) {
            throw new AccountServiceException("Internal Server Exception while getting exception");
        }
    }
}
