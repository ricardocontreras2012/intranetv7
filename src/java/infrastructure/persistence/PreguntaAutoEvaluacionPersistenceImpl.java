/*
 * @(#)PregEnctaPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.PreguntaAutoEvaluacionPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.PreguntaAutoEvaluacion;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.sqlRestriction;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class PreguntaAutoEvaluacionPersistenceImpl extends CrudAbstractDAO<PreguntaAutoEvaluacion, Long> implements PreguntaAutoEvaluacionPersistence {

    @SuppressWarnings("unchecked")
    @Override
    public List<PreguntaAutoEvaluacion> find(Integer agno, Integer sem) {
        Criteria criteria = getSession().createCriteria(PreguntaAutoEvaluacion.class);
        String filter = "EXISTS (SELECT * FROM encuesta_docente WHERE edo_tipo='I' AND edo_agno = "+agno+" AND edo_ciclo="+sem+" AND edo_nro = pad_enc)";        

        criteria.add(sqlRestriction(filter));
        criteria.addOrder(asc("id.padPreg"));

        return criteria.list();
    }
}
