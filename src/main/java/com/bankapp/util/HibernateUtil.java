package com.bankapp.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.LoggerFactory;

public class HibernateUtil{
        public static final SessionFactory sessionFactory;
        public static final org.slf4j.Logger logger = LoggerFactory.getLogger(HibernateUtil.class);

        static {
            try {
                sessionFactory = new Configuration().configure().buildSessionFactory();
            } catch (HibernateException e) {
                logger.error("Error while initializing session");
                throw new RuntimeException(e);
            }
        }
        public static SessionFactory getSessionFactory(){
            return sessionFactory;
        }
    }