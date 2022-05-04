package com.avitepa.foundation.bank.service.impl;

import com.avitepa.foundation.bank.common.TestConstants;
import com.avitepa.foundation.bank.exceptionhandling.AccountServiceException;
import com.avitepa.foundation.bank.model.Account;
import com.avitepa.foundation.bank.model.Customer;
import com.avitepa.foundation.bank.repository.AccountRepository;
import com.avitepa.foundation.bank.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.channels.AcceptPendingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private CustomerRepository customerRepository;

    Account account;
    Customer customer;

    @BeforeEach
    public void setUp() {
        account = Account.builder().accountNumber(TestConstants.ACC_NO)
                        .accountType(TestConstants.ACC_TYPE)
                                .accountStatus(TestConstants.ACC_STATUS)
                                        .balance(TestConstants.BALANCE)
                                                .dateOpened(TestConstants.DATE_OPENED).build();
        account.setAccountNumber(TestConstants.ACC_NO);
        account.setAccountType(TestConstants.ACC_TYPE);
        account.setAccountStatus(TestConstants.ACC_STATUS);
        account.setBalance(TestConstants.BALANCE);
        account.setDateOpened(TestConstants.DATE_OPENED);

        customer  = Customer.builder().customerId(TestConstants.CUST_ID)
                .firstName(TestConstants.FIRST_NAME)
                .lastName(TestConstants.LAST_NAME)
                .address(TestConstants.ADDRESS)
                .branch(TestConstants.BRANCH).build();
    }

    @Test
    public void addAccount_Test() throws AccountServiceException {
        int accountNumber  = TestConstants.ACC_NO;
        when(accountRepository.save(account)).thenReturn(account);

        Account addedAccount = accountService.addAccount(account);
        assertEquals(accountNumber, addedAccount.getAccountNumber());
    }

    @Test
    public void getAllAccounts_Test() throws AccountServiceException {
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        accounts.add(account);

        when(accountRepository.findAll()).thenReturn(accounts);
        List<Account> allAccounts = accountService.getAllAccounts();
        assertThat(allAccounts.size()).isGreaterThan(0);
    }

    @Test
    public void getAllCustomers_Test() throws AccountServiceException {
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        customers.add(customer);

        when(customerRepository.findAll()).thenReturn(customers);
        List<Customer> retrievedCustomers = accountService.getAllCustomers();
        assertThat(retrievedCustomers.size()).isGreaterThan(0);
    }

    @Test
    public void addCustomer_Test() throws AccountServiceException {
        when(customerRepository.save(customer)).thenReturn(customer);
        Customer addedCustomer = accountService.addCustomer(customer);
        assertThat(addedCustomer.getCustomerId()).isNotNull();
    }

    @Test
    public void getBalanceOf_Test() throws AccountServiceException {
        when(accountRepository.findById(TestConstants.ACC_NO)).thenReturn(Optional.of(account));
        Optional<Account> retrievedAccount = accountService.getBalanceOf(TestConstants.ACC_NO);
        assertEquals(TestConstants.BALANCE, retrievedAccount.get().getBalance());
    }

}
