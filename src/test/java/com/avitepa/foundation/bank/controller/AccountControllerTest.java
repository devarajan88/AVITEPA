package com.avitepa.foundation.bank.controller;

import com.avitepa.foundation.bank.common.TestConstants;
import com.avitepa.foundation.bank.controller.AccountController;
import com.avitepa.foundation.bank.model.Account;
import com.avitepa.foundation.bank.model.Customer;
import com.avitepa.foundation.bank.service.AccountService;
import com.avitepa.foundation.bank.service.impl.AccountServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest({AccountController.class})
public class  AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountServiceImpl accountService;

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
    public void addAccount_Test() throws Exception {
        when(accountService.addAccount(account)).thenReturn(account);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/accounts/add")
                .content(asJsonString(customer))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("SUCCESS"));

    }

    @Test
    public void getAllAccounts_Test() throws Exception {
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        when(accountService.getAllAccounts()).thenReturn(accounts);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/accounts/all")
                        .content(asJsonString(customer))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    public void getAllCustomers_Test() throws Exception {
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        when(accountService.getAllCustomers()).thenReturn(customers);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/all")
                        .content(asJsonString(customer))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    public void transferFunds_Test() throws Exception {
        when(accountService.transferFunds(101, 202, 25.55)).thenReturn("SUCCESS");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/accounts/transfer")
                        .content(asJsonString(customer))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
