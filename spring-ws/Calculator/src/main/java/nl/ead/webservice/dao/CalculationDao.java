package nl.ead.webservice.dao;

import javax.persistence.*;
import nl.ead.webservice.model.Calculation;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description for the class CalculationDao:
 * <p/>
 * Example usage:
 * <p/>
 * <
 * pre>
 *
 * </pre>
 *
 * @author mdkr
 * @version Copyright (c) 2012 HAN University, All rights reserved.
 */
@Repository
public class CalculationDao implements ICalculationDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void save(Calculation calculation) {
        em.persist(calculation);
    }
}
