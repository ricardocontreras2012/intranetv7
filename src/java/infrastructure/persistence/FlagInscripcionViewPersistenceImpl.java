/*
 * @(#)FlagInscripcionViewPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import infrastructure.persistence.dao.CrudAbstractDAO;
import org.hibernate.Criteria;
import static org.hibernate.criterion.Restrictions.eq;
import domain.model.FlagInscripcionView;
import domain.repository.FlagInscripcionViewRepository;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class FlagInscripcionViewPersistenceImpl extends CrudAbstractDAO<FlagInscripcionView, Long>
        implements FlagInscripcionViewRepository {

    /**
     * Method description
     *
     * @param carrera
     * @param mencion
     * @return
     */
    @Override
    public FlagInscripcionView find(Integer carrera, Integer mencion) {
        Criteria criteria = getSession().createCriteria(FlagInscripcionView.class);

        criteria.add(eq("paramCar", carrera));
        criteria.add(eq("paramMen", mencion));

        return (FlagInscripcionView) criteria.uniqueResult();                 
    }
}
