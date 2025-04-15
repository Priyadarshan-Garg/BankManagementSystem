package com.bankapp.util;

import com.bankapp.service.*;
import com.bankapp.model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.LoggerFactory;


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
    }

}

