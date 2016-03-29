package nl.han.dare2date.service.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

@Repository
public class SimpleDao {
    //@PersistenceContext
    private EntityManager em;

    public SimpleDao()
    {
        em = Persistence.createEntityManagerFactory("calculator").createEntityManager();
    }
    @Transactional
    public void save(Calculation calculation) {
        em.getTransaction().begin();
        em.persist(calculation);
        em.getTransaction().commit();
    }
}
