package com.avitepa.foundation.bank.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity(name = "CUSTOMER")
public class Customer extends Auditable<String> implements Serializable {

    @Id
    @Column
    private Long customerId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String address;

    @Column
    private String branch;

    @Column
    private String mobileNo;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "customer")
    private Account account;

}
