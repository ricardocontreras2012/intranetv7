/*
 * @(#)LogInscripcionPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.LogInscripcionPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.AluCar;
import domain.model.LogInscripcion;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.sql.JoinType.LEFT_OUTER_JOIN;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class LogInscripcionPersistenceImpl extends CrudAbstractDAO<LogInscripcion, Long> implements LogInscripcionPersistence {

    /**
     * Method description
     *
     * @param aluCar
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<LogInscripcion> find(AluCar aluCar) {
          
        Criteria criteria = getSession().createCriteria(LogInscripcion.class);
        criteria.createAlias("procesoInscripcion", "procesoInscripcion");
        criteria.createAlias("aluCar", "aluCar");
        criteria.createAlias("asignatura", "asignatura");
        criteria.createCriteria("curso", LEFT_OUTER_JOIN);
        criteria.createCriteria("electivo", LEFT_OUTER_JOIN);
        criteria.add(eq("aluCar.id", aluCar.getId()));
        criteria.addOrder(asc("logCorrel"));
        return criteria.list();        
        
    }
    
    /**
     * Method description
     *
     * @param aluCar
     * @param sem
     * @param agno
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<LogInscripcion> findAgnoSem(AluCar aluCar, Integer sem, Integer agno) {
        Criteria criteria = getSession().createCriteria(LogInscripcion.class);
        criteria.createAlias("procesoInscripcion", "procesoInscripcion");
        criteria.createAlias("aluCar", "aluCar");
        criteria.createAlias("asignatura", "asignatura");
        criteria.createCriteria("curso", LEFT_OUTER_JOIN);
        criteria.createCriteria("electivo", LEFT_OUTER_JOIN);
        criteria.add(eq("aluCar.id", aluCar.getId()));
        criteria.add(eq("logSem", sem));
        criteria.add(eq("logAgno", agno));
        criteria.addOrder(asc("logCorrel"));
        return criteria.list();
    }
}
