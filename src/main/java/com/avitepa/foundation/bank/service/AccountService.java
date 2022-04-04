package com.avitepa.foundation.bank.service;

import com.avitepa.foundation.bank.model.Account;
import com.avitepa.foundation.bank.model.Customer;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    public Account addAccount(Account account);

    public List<Account> getAllAccounts();

    public String transferFunds(int from, int to, double amount);

    public List<Customer> getAllCustomers();

    public Optional<Account> getBalanceOf(int accountNumber);

    Customer addCustomer(Customer cust);
}
