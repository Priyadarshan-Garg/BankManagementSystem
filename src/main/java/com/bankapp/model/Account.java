package com.bankapp.model;

import org.hibernate.Cache;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Entity
@Table(name = "account")
public class Account {
    public enum accountType {
        Savings, Current, FixedDeposit, Salary, NRI, SeniorCitizen, Minor, Corporate;
    }

    @Id  // this 'll be the unique id
    @Column(length = 12, unique = true, nullable = false)
    // nullable --> DB me acc_num kabhi null nahi hoga / ye alag table banayega
    private String acc_num;

    private double balance;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private accountType type;

    @ManyToOne      // jis class me likha vo pehla word depit karta hai next word jo entity hai
    @JoinColumn(name = "user_id", nullable = false)
    private User primaryUser;

    @ManyToMany()
    @JoinTable(name = "account_authorised_users", joinColumns = @JoinColumn(name = "account_num")
            , inverseJoinColumns = @JoinColumn(name = "user_id"))
    List<User> authorisedUsers = new ArrayList<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)   // kyuki agar Account hi delete hua to sare transactions bhi
    List<Transaction> transactionList = new ArrayList<Transaction>();


    public Account() {
        this.type = accountType.Savings;
    }

    public Account(double balance, accountType type, User user) {
        this.acc_num = generateAccNum();
        this.balance = balance;
        this.type = type;
        this.primaryUser = user;
    }

    public User getUser() {
        return primaryUser;
    }

    public void setUser(User user) {
        this.primaryUser = user;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public accountType getType() {
        return type;
    }

    public void setAcc_num(String acc_num) {
        if (this.acc_num != null) {
            throw new UnsupportedOperationException("Account number cannot be changed once set.");
        } else
            this.acc_num = acc_num;
    }

    public String getAcc_num() {
        return acc_num;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public User getPrimaryUser() {
        return primaryUser;
    }

    public void setPrimaryUser(User primaryUser) {
        this.primaryUser = primaryUser;
    }

    public List<User> getAuthorisedUsers() {
        return authorisedUsers;
    }

    public void setAuthorisedUsers(List<User> authorisedUsers) {
        this.authorisedUsers = authorisedUsers;
    }

    public String generateAccNum() {
        long min = 100000000000L;
        long max = 999999999999L;
        return String.valueOf(min + (long) (Math.random() * (max - min)));

    }

    public void setType(accountType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Account Number : " + acc_num +
                " Balance : " + balance +
                " Account type : " + type +
                " User name : " + primaryUser.getUserName();

    }


}
