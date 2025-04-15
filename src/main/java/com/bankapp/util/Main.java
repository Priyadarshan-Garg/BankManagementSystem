package com.bankapp.util;

import com.bankapp.service.*;
import com.bankapp.model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.LoggerFactory;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws Exception {
//        User user =  new User("Priyadarshan","Delhi");
//        BankService bankService = new BankService(user);
//        bankService.createNewAcc(127834, Account.accountType.Savings,user);
//        bankService.createNewAcc(213456623456.23, Account.accountType.Corporate,user);
//        bankService.showAllAccounts(user);
//        System.out.println(bankService.ShowBalance("999735345707"));
//        try{
//        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
//            Session session = sessionFactory.openSession();
//            session.beginTransaction();
//            session.save(user);
//            session.getTransaction().commit();
//        } catch (HibernateException e) {
//            e.getStackTrace();
//        }
//         User user1 = new User("Radhe","Delhi");
//         BankService service1 = new BankService(user1);
//         BankService service2 = new BankService(user1);
//         User user2 = new User("Shyam","Kanpur");


//    logger.info("App started");

//         service1.createNewAcc(123456, Account.accountType.Savings,user1);
//         service2.createNewAcc(121345657, Account.accountType.Current,user2);


//         service1.transfer(72484,"982013350550","632606444135");
//         service1.transfer(850,"632606444135","982013350550");
//        System.out.println(service1.ShowBalance("465150682060"));
//
//        double balance = service1.ShowBalance("465150682060");
//        String formatted = String.format("%.2f", balance);
//        System.out.println(formatted);
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to CLI BankApp");
        System.out.println("1. Register");
        System.out.println("2. Login");
        int choice = Integer.parseInt(sc.nextLine());

        User user = null;

        if (choice == 1) {
            user = AuthService.register(sc);  // Scanner pass kar de
        } else if (choice == 2) {
            user = LoginService.login(sc);
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
                  bankService.createNewAcc(123456, Account.accountType.Savings,user);
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

