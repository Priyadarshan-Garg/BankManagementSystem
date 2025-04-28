package com.bankapp.DAO;

import com.bankapp.model.Account;
import com.bankapp.model.User;
import com.bankapp.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AccountDAO {
    public static final Logger logger = LoggerFactory.getLogger(AccountDAO.class);

    public static void saveOrUpdate(Account account) {
        Transaction tx = null;
        try (Session session = HibernateUtil.sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(account);
            tx.commit();
            session.close();
        } catch (HibernateException e) {
            logger.error("Error while saving/creating account for {}", account.getUser().getUserName());
            if ((tx != null)) tx.rollback();
            throw e;
        }
    }

public static void remove(String accNum) {
    Transaction tx = null;
    Account account = getAccountByAccNum(accNum);
    if (account == null) {
        logger.error("Account not found: {}", accNum);
        return;
    }

    try (Session session = HibernateUtil.sessionFactory.openSession()) {
        tx = session.beginTransaction();
            
        // Direct account delete karo, transactions automatically delete honge due to cascade
        String hql = "DELETE FROM Account WHERE acc_num = :accNum";
        session.createQuery(hql)
            .setParameter("accNum", accNum)
            .executeUpdate();
            
        tx.commit();
        logger.info("Account {} deleted successfully", accNum);
            
    } catch (Exception e) {
        if (tx != null && tx.isActive()) {
            try {
                tx.rollback();
            } catch (Exception rollbackEx) {
                logger.error("Error during rollback", rollbackEx);
            }
        }
        logger.error("Error while deleting account {}", accNum, e);
        throw new RuntimeException("Failed to delete account: " + e.getMessage());
    }
}

    public static Account getAccountByAccNum(String accNum) {
        Session session = HibernateUtil.sessionFactory.openSession();
        session.beginTransaction();

        Account acc = session.createQuery("from Account where acc_num = :accNum", Account.class)
                .setParameter("accNum", accNum)
                .uniqueResult();

        session.getTransaction().commit();
        session.close();

        return acc;
    }

    public static List<Account> getAccount(User user){
        int userId = UserDAO.getUserById(user.getUserName());
        List<Account> accounts = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            System.out.println("Session is opened");
            String hql = "FROM Account WHERE primaryUser.id  = :userId";
             accounts = session.createQuery(hql, Account.class)
                    .setParameter("userId", userId).setParameter("userId",user.getId()).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accounts;
    }

}