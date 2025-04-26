package com.bankapp.DAO;

import com.bankapp.model.User;
import com.bankapp.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDAO {
    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);
    
    public static void saveOrUpdate(User user) {
        Transaction tx = null;
        try (Session session = HibernateUtil.sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(user);
            tx.commit();
            logger.info("User {} saved/updated successfully", user.getUserName());
        } catch (Exception e) {
            if (tx != null && tx.getStatus().canRollback()) {
                tx.rollback();
            }
            logger.error("Error while saving user {}", user.getUserName(), e);
            throw e;
        }
    }

    public static void remove(User user) {
        Transaction tx = null;
        try (Session session = HibernateUtil.sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.delete(user);
            tx.commit();
            logger.info("User {} removed successfully", user.getUserName());
        } catch (Exception e) {
            if (tx != null && tx.getStatus().canRollback()) {
                tx.rollback();
            }
            logger.error("Error while removing user {}", user.getUserName(), e);
            throw e;
        }
    }

    public static User getUserByUsername(String username) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            User user = session.createQuery("FROM User WHERE userName = :uname", User.class)
                    .setParameter("uname", username)
                    .uniqueResult();
            if (user == null) {
                logger.debug("No user found with username: {}", username);
            }
            return user;
        } catch (Exception e) {
            logger.error("Error while fetching user with username: {}", username, e);
            throw e;
        }
    }
}