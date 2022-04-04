package com.avitepa.foundation.bank.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity(name = "ACCOUNT")
public class Account extends Auditable<String> implements Serializable {

    @Id
    @Column
    private int accountNumber;

    @Column
    private AccountType accountType;

    @Column
    private double balance;

    @Column
    private String accountStatus;

    @Column
    private Date dateOpened;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "customerId")
    private Customer customer;
}
