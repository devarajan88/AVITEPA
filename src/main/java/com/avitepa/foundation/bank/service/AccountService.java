package com.avitepa.foundation.bank.service;

import com.avitepa.foundation.bank.exceptionhandling.AccountServiceException;
import com.avitepa.foundation.bank.model.Account;
import com.avitepa.foundation.bank.model.Customer;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    public Account addAccount(Account account)  throws AccountServiceException;

    public List<Account> getAllAccounts() throws AccountServiceException;

    public String transferFunds(int from, int to, double amount) throws AccountServiceException;

    public List<Customer> getAllCustomers() throws AccountServiceException;

    public Optional<Account> getBalanceOf(int accountNumber) throws AccountServiceException;

    Customer addCustomer(Customer cust) throws AccountServiceException;
}
