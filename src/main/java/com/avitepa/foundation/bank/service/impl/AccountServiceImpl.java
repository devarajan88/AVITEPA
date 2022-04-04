package com.avitepa.foundation.bank.service.impl;

import com.avitepa.foundation.bank.model.Account;
import com.avitepa.foundation.bank.model.Customer;
import com.avitepa.foundation.bank.repository.AccountRepository;
import com.avitepa.foundation.bank.repository.CustomerRepository;
import com.avitepa.foundation.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AccountServiceImpl implements AccountService {

    enum TRANSFER_STATUS { SUCCESS, ID_MISMATCH, INSUFFICIENT_BALANCE }

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Account addAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public String transferFunds(int from, int to, double amount) {
        String transferStatus = null;
        double debitedBalance, creditedBalance;
        Optional<Account> fromAccount = accountRepository.findById(Integer.valueOf(from));
        Optional<Account> toAccount = accountRepository.findById(Integer.valueOf(to));
        if (fromAccount.isPresent() && toAccount.isPresent()) {
            if(fromAccount.get().getBalance() >  amount) {
                debitedBalance = fromAccount.get().getBalance() -  amount;
                creditedBalance = toAccount.get().getBalance() + amount;
                fromAccount.get().setBalance(debitedBalance);
                toAccount.get().setBalance(creditedBalance);
                accountRepository.save(fromAccount.get());
                accountRepository.save(toAccount.get());
                transferStatus = TRANSFER_STATUS.SUCCESS.toString();
            } else {
                transferStatus = TRANSFER_STATUS.INSUFFICIENT_BALANCE.toString();
            }
        }

        return transferStatus;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Account> getBalanceOf(int accountNumber) {
        return accountRepository.findById(Integer.valueOf(accountNumber));
    }

    @Override
    public Customer addCustomer(Customer cust) {
        return customerRepository.save(cust);
    }
}
