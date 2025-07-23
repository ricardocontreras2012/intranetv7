/*
 * @(#)EncuestaDocentePersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.EncuestaDocenteRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.EncuestaDocente;
import org.hibernate.Criteria;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class EncuestaDocentePersistenceImpl extends CrudAbstractDAO<EncuestaDocente, Long> implements EncuestaDocenteRepository {

    /**
     * Method description
     *
     * @param agno
     * @param sem
     * @param carrera
     * @param mencion
     * @param tipo
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public EncuestaDocente find(Integer agno, Integer sem, Integer carrera, Integer mencion, String tipo) {
        Criteria criteria = getSession().createCriteria(EncuestaDocente.class);

        criteria.createAlias("encuestaCarreras", "encuestaCarreras");
        criteria.add(eq("edoAgno", agno));
        criteria.add(eq("edoCiclo", sem));
        criteria.add(eq("edoTipo", tipo));
        criteria.add(eq("encuestaCarreras.id.edcCodCar", carrera));
        criteria.add(eq("encuestaCarreras.id.edcCodMen", mencion));
        criteria.addOrder(asc("edoNro"));

        return (EncuestaDocente) criteria.uniqueResult();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public EncuestaDocente findAutoEval(Integer agno, Integer sem) {
        Criteria criteria = getSession().createCriteria(EncuestaDocente.class);

        criteria.add(eq("edoAgno", agno));
        criteria.add(eq("edoCiclo", sem));
        criteria.add(eq("edoTipo", "I"));
        criteria.addOrder(asc("edoNro"));

        return (EncuestaDocente) criteria.uniqueResult();
    }    
}
