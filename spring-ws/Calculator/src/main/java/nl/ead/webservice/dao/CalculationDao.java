package nl.ead.webservice.dao;

import nl.ead.webservice.model.Calculation;

import javax.persistence.*;

/**
 * Description for the class CalculationDao:
 * <p/>
 * Example usage:
 * <p/>
 * <pre>
 *
 * </pre>
 *
 * @author mdkr
 * @version Copyright (c) 2012 HAN University, All rights reserved.
 */
public class CalculationDao implements ICalculationDao {
    public void save(Calculation calculation) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("calculator");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(calculation);
            tx.commit();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
