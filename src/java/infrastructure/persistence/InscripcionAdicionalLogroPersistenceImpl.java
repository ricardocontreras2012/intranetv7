/*
 * @(#)InscripcionAdicionalLogroPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.InscripcionAdicionalLogroRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.InscripcionAdicionalLogro;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.FetchMode.JOIN;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class InscripcionAdicionalLogroPersistenceImpl extends CrudAbstractDAO<InscripcionAdicionalLogro, Long>
        implements InscripcionAdicionalLogroRepository {

    /**
     * Method description
     *
     * @param agnoIns
     * @param semIns
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<InscripcionAdicionalLogro> find(Integer agnoIns, Integer semIns,
            Integer trequisito) {
        Criteria criteria = getSession().createCriteria(InscripcionAdicionalLogro.class);

        criteria.setFetchMode("trequisitoLogroAdicional", JOIN);
        criteria.createAlias("aluCar", "aluCar");
        criteria.createAlias("aluCar.alumno", "alumno");
        criteria.add(eq("id.ialReqAdic", trequisito));
        criteria.add(eq("id.ialAgno", agnoIns));
        criteria.add(eq("id.ialSem", semIns));
        criteria.addOrder(asc("alumno.aluPaterno"));
        criteria.addOrder(asc("alumno.aluMaterno"));
        criteria.addOrder(asc("alumno.aluNombre"));

        return criteria.list();
    }

    /**
     * Method description
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<InscripcionAdicionalLogro> findxCalificar(Integer trequisito) {
        return null;
    }
}
