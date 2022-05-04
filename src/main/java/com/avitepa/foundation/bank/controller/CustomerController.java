package com.avitepa.foundation.bank.controller;

import com.avitepa.foundation.bank.exceptionhandling.AccountServiceException;
import com.avitepa.foundation.bank.model.Account;
import com.avitepa.foundation.bank.model.Customer;
import com.avitepa.foundation.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/customers")
    public ResponseEntity getAllCustomers() throws AccountServiceException {
        List<Customer> customers = accountService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping("/customers/add")
    public ResponseEntity addAccount(@RequestBody Customer cust) throws AccountServiceException {
        accountService.addCustomer(cust);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
}
