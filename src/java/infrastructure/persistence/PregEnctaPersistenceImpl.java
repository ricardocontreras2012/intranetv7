/*
 * @(#)PregEnctaPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.PregEnctaRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.EncuestaDocente;
import domain.model.PregEncta;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class PregEnctaPersistenceImpl extends CrudAbstractDAO<PregEncta, Long> implements PregEnctaRepository {

    @SuppressWarnings("unchecked")
    @Override
    public List<PregEncta> find(EncuestaDocente encuesta) {
        Criteria criteria = getSession().createCriteria(PregEncta.class);
        criteria.add(eq("id.pregEnc", encuesta.getEdoNro()));
        criteria.addOrder(asc("id.pregNro"));

        return criteria.list();
    }
}
