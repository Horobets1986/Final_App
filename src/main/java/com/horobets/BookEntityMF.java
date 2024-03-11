package com.horobets;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
class BookEntityMF {
    private EntityManagerFactory emFactory;
    EntityManager getEntityManager(){
        emFactory = Persistence.createEntityManagerFactory("BookPersistenceUnit");
        return emFactory.createEntityManager();
    }
    void close(){
        emFactory.close();
    }
}
