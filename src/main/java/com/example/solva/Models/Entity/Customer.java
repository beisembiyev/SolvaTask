package com.example.solva.Models.Entity;


import com.example.solva.Models.Enums.SpendingsCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int accountNum;
    private String firstName;
    private String lastName;
    private String email;
    private double balance;
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name="customer_id")
    private Map<SpendingsCategory, Limit> limits=new HashMap();

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "customer_id")
    private List<Transaction> transactions;

    public Customer(int accountNum, String firstName, String lastName, String email, double balance, HashMap<SpendingsCategory,Limit> limits, List<Transaction> transactions) {
        this.accountNum = accountNum;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.balance = balance;
        this.limits = limits;
        this.transactions = transactions;
    }
}
