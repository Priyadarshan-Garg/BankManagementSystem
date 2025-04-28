package com.bankapp.service;

import com.bankapp.DAO.AccountDAO;
import com.bankapp.DAO.TransactionDAO;
import com.bankapp.DAO.UserDAO;
import com.bankapp.model.Account;
import com.bankapp.model.User;
import com.bankapp.model.Transaction;
import com.bankapp.util.*;
import org.hibernate.LazyInitializationException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.util.List;


public class BankService {
    private final User user; // jab koi existing user bank jayea to uska data isse fetch karunga
    public static final Logger logger = LoggerFactory.getLogger(BankService.class);
    // isme sab accounts store karenge


    public BankService(User user) {
        this.user = user;
    }



    public void createNewAcc(double balance, Account.accountType type, User user) {
    org.hibernate.Transaction tx = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        tx = session.beginTransaction();
        
        // Refresh user with session
        user = session.get(User.class, user.getId());
        
        Account account = new Account(balance, type, user);
        user.addAccount(account);  // Using addAccount method instead of direct list access
        
        session.saveOrUpdate(user);
        session.saveOrUpdate(account);
        
        tx.commit();
        logger.info("Account created successfully by user {} with balance {} and type {}", 
                   user.getUserName(), balance, type);
    } catch (Exception e) {
        if (tx != null && tx.isActive()) {
            tx.rollback();
        }
        throw e;
    }
}

    public void closeAccount(String acc_num) {
            AccountDAO.remove(acc_num);
    }

    public void showAllAccounts(User user) {
            List<Account> accounts = AccountDAO.getAccount(user);
            assert accounts != null;
             System.out.println("Found " + accounts.size() + " accounts of user "+user.getUserName());
            accounts.forEach(Account-> System.out.println(Account.getAcc_num()));
    }

    public void deposit(double amount, String accNum) {
        if(amount<=0){
            logger.warn("User tried deposit unreal amount {}",amount);
            System.out.println("Invalid amount");
            return;
        }
        Account account = AccountDAO.getAccountByAccNum(accNum);
        if (account.getAcc_num() != null) {
            Transaction newTransaction = new Transaction(Transaction.TransactionType.deposit, account, amount);
            account.setBalance(account.getBalance() + amount);
            AccountDAO.saveOrUpdate(account);
            TransactionDAO.saveOrUpdate(newTransaction);
            System.out.println("Amount deposited successfully " + amount);
            logger.info("Amount deposited successfully by user {} with balance {} and type {}", user.getUserName(), amount, account.getType());
        } else {
            System.out.println("Account was not found");
            logger.warn("Account was not found by user {}", user.getUserName());
            return;
        }

    }

    public String ShowBalance(String accNum) throws NumberFormatException {
        Account account = AccountDAO.getAccountByAccNum(accNum);
        if (account != null) {
            return String.format("%.2f",account.getBalance());
        }

        return "0.0";
    }

    public void withdraw(double amount, String accNum) {
        Account account = AccountDAO.getAccountByAccNum(accNum);
        if(amount<=0 || amount>account.getBalance()){
            logger.warn("User tried withdraw unreal amount {}",amount);
            System.out.println("Invalid amount or insufficient balance in account");
            return;
        }
        if (account.getAcc_num() != null) {
            if (amount < account.getBalance()) {
                Transaction newTransaction = new Transaction(Transaction.TransactionType.withdraw, account, amount);
                account.setBalance(account.getBalance() - amount);
                AccountDAO.saveOrUpdate(account);
                TransactionDAO.saveOrUpdate(newTransaction);
                logger.info("Amount withdrawal successfully by user {} with amount {} from account {}",user.getUserName(),amount,accNum);
                System.out.println("Amount withdrawal " + amount);
            }
        }

    }

    public void transfer(double amount, String fromAcc, String toAcc) {
        if (amount <= 0) {
            logger.warn("User tried transfer unreal amount {}", amount);
            System.out.println("Invalid amount");
            return;
        }
        Account sender = null;
        sender = AccountDAO.getAccountByAccNum(fromAcc);
        if (sender.getAcc_num() == null) {
            logger.warn("Sender's account not found by user {}",user.getUserName());
            System.out.println("Sender's account not found.");
            return;
        }
        if (sender.getBalance() < amount) {
            logger.warn("insufficient balance in sender's account {}", sender.getBalance());
            System.out.println("Insufficient balance");
            return;
        }
        Account receiver = null;
        receiver = AccountDAO.getAccountByAccNum(toAcc);

        if (receiver.getAcc_num() == null) {
            logger.warn("Receiver's account not fount");
            System.out.println("Receiver's account not found.");
            return;
        }
        receiver.setBalance(receiver.getBalance() + amount);
        AccountDAO.saveOrUpdate(receiver);
        Transaction reciever_transaction = new Transaction(Transaction.TransactionType.transferCredited, sender, amount);
        receiver.getTransactionList().add(reciever_transaction);
        TransactionDAO.saveOrUpdate(reciever_transaction);
        sender.setBalance(sender.getBalance() - amount);
        Transaction sender_transaction = new Transaction(Transaction.TransactionType.transferDeducted, receiver, amount);
        AccountDAO.saveOrUpdate(sender);
        sender.getTransactionList().add(sender_transaction);
        TransactionDAO.saveOrUpdate(sender_transaction);
        logger.info("Amount {} successfully transferred from {} to {}",amount,fromAcc,toAcc);
        System.out.println("Transfer completed");
        return;
    }

    public void showLastTransactions(int n, String accNum) {
        for (Account acc : user.getAccountList()) {
            if (acc.getAcc_num().equals(accNum)) {
                int size = acc.getTransactionList().size();
                int start = Math.max(size - n, 0);
                for (int i = start; i < size; i++) {
                    System.out.println(acc.getTransactionList().get(i));
                    ;  // ya println
                }

            }
        }
    }

    public void showFirstTransactions(int n, String accNum) {
        for (Account acc : user.getAccountList()) {
            if (acc.getAcc_num().equals(accNum)) {
                int limit = Math.min(n, acc.getTransactionList().size());
                for (int i = 0; i < limit; i++) {
                    System.out.println(acc.getTransactionList().get(i));
                }


            }
        }
    }

    public void showAllTransactions(String accNum) {
        for (Account acc : user.getAccountList()) {
            if (acc.getAcc_num().equals(accNum)) {
                for (int i = 0; i < acc.getTransactionList().size(); i++) {
                    System.out.println(acc.getTransactionList().get(i));
                }
            }
        }
    }

}