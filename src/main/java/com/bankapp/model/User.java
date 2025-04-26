package com.bankapp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bank_user")
public class User {
    @Column(name = "userNed ame",nullable = false)
    private String userName;
    @Column(name = "password",nullable = false)
    private String password;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String address;
    @ManyToMany(mappedBy = "authorisedUsers")
    List<Account> authorisedAccounts = new ArrayList<>();

    //    private Account account;
    @OneToMany(mappedBy = "primaryUser", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    // cuz one user could have more than 1 account many to one nahi cuz we are in user class so (one) user can have (many) accounts
            List<Account> accountList = new ArrayList<Account>();

    public User() {
        this.userName = "userName";
        this.password = "password";
        this.address = "userAddress";
    }
    public User(String userName,String password,String address) {
        this.userName = userName;
        this.password = password;
        this.address = address;
    }
//    public User(String userName, String address) {
//        this.userName = userName;
//        this.address = address;
//    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
}
