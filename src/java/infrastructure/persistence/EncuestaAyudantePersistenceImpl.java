/*
 * @(#)EncuestaAyudantePersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.EncuestaAyudanteRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.EncuestaAyudante;
import org.hibernate.Criteria;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class EncuestaAyudantePersistenceImpl extends CrudAbstractDAO<EncuestaAyudante, Long> implements EncuestaAyudanteRepository {

    /**
     * Method description
     *
     * @param agno
     * @param sem
     * @param carrera
     * @param mencion
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public EncuestaAyudante find(Integer agno, Integer sem, Integer carrera, Integer mencion, String tipo) {
        Criteria criteria = getSession().createCriteria(EncuestaAyudante.class);

        criteria.createAlias("encuestaCarreras", "encuestaCarreras");
        criteria.add(eq("enaAgno", agno));
        criteria.add(eq("enaCiclo", sem));
        criteria.add(eq("enaTipo", tipo));
        criteria.add(eq("encuestaCarreras.id.eacCodCar", carrera));
        criteria.add(eq("encuestaCarreras.id.eacCodMen", mencion));
        criteria.addOrder(asc("enaNro"));

        return (EncuestaAyudante) criteria.uniqueResult();
    }
}
