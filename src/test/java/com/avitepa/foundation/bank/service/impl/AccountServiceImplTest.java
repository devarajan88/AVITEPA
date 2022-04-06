package com.avitepa.foundation.bank.service.impl;

import com.avitepa.foundation.bank.common.TestConstants;
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
    Customer customer = new Customer();

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

        customer.setCustomerId(TestConstants.CUST_ID);
        customer.setFirstName(TestConstants.FIRST_NAME);
        customer.setLastName(TestConstants.LAST_NAME);
        customer.setAddress(TestConstants.ADDRESS);
        customer.setBranch(TestConstants.BRANCH);
    }

    @Test
    public void addAccount_Test() {
        int accountNumber  = TestConstants.ACC_NO;
        when(accountRepository.save(account)).thenReturn(account);

        Account addedAccount = accountService.addAccount(account);
        assertEquals(accountNumber, addedAccount.getAccountNumber());
    }

    @Test
    public void getAllAccounts_Test() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        accounts.add(account);

        when(accountRepository.findAll()).thenReturn(accounts);
        List<Account> allAccounts = accountService.getAllAccounts();
        assertThat(allAccounts.size()).isGreaterThan(0);
    }

    @Test
    public void getAllCustomers_Test() {
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        customers.add(customer);

        when(customerRepository.findAll()).thenReturn(customers);
        List<Customer> retrievedCustomers = accountService.getAllCustomers();
        assertThat(retrievedCustomers.size()).isGreaterThan(0);
    }

    @Test
    public void addCustomer_Test() {
        when(customerRepository.save(customer)).thenReturn(customer);
        Customer addedCustomer = accountService.addCustomer(customer);
        assertThat(addedCustomer.getCustomerId()).isNotNull();
    }

    @Test
    public void getBalanceOf_Test() {
        when(accountRepository.findById(TestConstants.ACC_NO)).thenReturn(Optional.of(account));
        Optional<Account> retrievedAccount = accountService.getBalanceOf(TestConstants.ACC_NO);
        assertEquals(TestConstants.BALANCE, retrievedAccount.get().getBalance());
    }

}
