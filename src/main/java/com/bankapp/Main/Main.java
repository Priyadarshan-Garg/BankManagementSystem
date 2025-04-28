package com.bankapp.Main;


import com.bankapp.model.Account;
import com.bankapp.model.User;
import com.bankapp.service.AuthService;
import com.bankapp.service.BankService;
import com.bankapp.service.LoginService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {


        Scanner sc = new Scanner(System.in);
        User user;
        System.out.println("Welcome to CLI BankApp");
        System.out.println("Choose one of the following options");
        System.out.println("1. Register");
        System.out.println("2. Login");
        int choice = Integer.parseInt(sc.nextLine());

        user = null;

        if (choice == 1) {
            user = AuthService.register(sc);  // Scanner pass kar de
        } else if (choice == 2) {
            System.out.println("Enter username :");
            String uname = sc.nextLine();
            System.out.println("Enter password :");
            String pwd = sc.nextLine();
            user = LoginService.login(uname, pwd);
        } else {
            System.out.println("Invalid choice. Exiting...");
            return;
        }

        BankService bankService = new BankService(user); // User object pass hoga usme

        while (true) {
            System.out.println("1. Create New Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Show Transactions");
            System.out.println("6. Exit");

            int service = Integer.parseInt(sc.nextLine());
            switch (service) {
                case 1 -> {
                    bankService.createNewAcc(123456, Account.accountType.Savings, user);
                }
                case 2 -> {
                    int amount = Integer.parseInt(sc.nextLine());
                    String accNum = sc.nextLine();
                    bankService.deposit(amount, accNum);
                }
                case 3 -> {
                    int amount = Integer.parseInt(sc.nextLine());
                    String accNum = sc.nextLine();
                    bankService.withdraw(amount, accNum);
                }
                case 4 -> {
                    int amount = Integer.parseInt(sc.nextLine());
                    String fromAccNum = sc.nextLine();
                    String toAccNum = sc.nextLine();
                    bankService.transfer(amount, toAccNum, fromAccNum);
                }
                case 5 -> {
                    String accountNum = sc.nextLine();
                    bankService.showAllTransactions(accountNum);
                }
                case 6 -> {
                    System.out.println("Thanks for using BankApp");
                    return;
                }
                default -> System.out.println("Invalid input");
            }
        }
    }

}

