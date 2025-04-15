package com.bankapp.DAO;

import com.bankapp.model.Transaction;
import com.bankapp.util.HibernateUtil;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransactionDAO {
    public static final Logger logger  = LoggerFactory.getLogger(TransactionDAO.class);
    public static void saveOrUpdate(Transaction transaction){
        org.hibernate.Transaction tx = null;
        try (Session session = HibernateUtil.sessionFactory.openSession()){
            tx = session.beginTransaction();
            session.saveOrUpdate(transaction);
            tx.commit();
        } catch (Exception e) {
            logger.error("Error while saving transaction {}",transaction.toString());
            if(tx!=null) tx.rollback();

            throw e;
        }



    }
}
