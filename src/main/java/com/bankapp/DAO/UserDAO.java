package com.bankapp.DAO;

import com.bankapp.model.User;
import com.bankapp.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;


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
            // Debug ke liye print karein
            System.out.println("Searching for user: " + username);
            
            String hql = "FROM User WHERE username = :username";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("username", username);
            
            // Debug ke liye result print karein
            User user = query.uniqueResult();
            System.out.println("Found user: " + (user != null ? "Yes" : "No"));
            
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static int getUserById(String username){
        int id = 0;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String hql = "From User WHERE userName = :username";
             id =  session.createQuery(hql, User.class).setParameter("username", username).uniqueResult().getId();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return id;
    }
}