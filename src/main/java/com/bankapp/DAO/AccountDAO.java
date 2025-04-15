package com.bankapp.DAO;

import com.bankapp.model.Account;
import com.bankapp.model.User;
import com.bankapp.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public static void remove(Account account) {
        Transaction tx = null;
        try (Session session = HibernateUtil.sessionFactory.openSession();) {
            tx = session.beginTransaction();
            session.delete(account);
            tx.commit();
        } catch (Exception e) {
            logger.error("Error while deleting account for {}", account.getUser().getUserName());
            if (tx != null) tx.rollback();
            throw e;
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

}
