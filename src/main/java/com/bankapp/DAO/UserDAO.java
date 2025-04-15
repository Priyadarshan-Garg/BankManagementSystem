package com.bankapp.DAO;

import com.bankapp.model.User;
import com.bankapp.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDAO {
    public static  final Logger logger = LoggerFactory.getLogger(UserDAO.class);
    public static void saveOrUpdate(User user){
        Transaction tx = null;
        try( Session session = HibernateUtil.sessionFactory.openSession()){
            tx = session.beginTransaction();
            session.saveOrUpdate(user);
             tx.commit();
        } catch (Exception e) {
            logger.error("Error while saving user {}", user.getUserName());
            throw e;
        }


    }
    public static void remove(User user){
        Transaction tx = null;
        try (Session session = HibernateUtil.sessionFactory.openSession()){
            tx = session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
        tx.commit();
        }
    }

    public static User getUserByUsername(String username) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM User WHERE userName = :uname", User.class)
                    .setParameter("uname", username)
                    .uniqueResult();
        }
    }

}
