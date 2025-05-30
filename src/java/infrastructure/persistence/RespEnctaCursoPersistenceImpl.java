/*
 * @(#)RespEnctaCursoPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.RespEnctaCursoPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.CursoId;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;
import domain.model.RespEnctaCursoView;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class RespEnctaCursoPersistenceImpl extends CrudAbstractDAO<RespEnctaCursoView, Long>
        implements RespEnctaCursoPersistence {

    /**
     * Method description
     *
     * @param cursoId
     * @param rutProf
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<RespEnctaCursoView> find(CursoId cursoId, Integer rutProf, String tipo) {
        getSession().clear();    // Borra la cache de session (sino repite valores anteriores)
        
        Criteria criteria = getSession().createCriteria(RespEnctaCursoView.class);

        criteria.add(eq("respAsign", cursoId.getCurAsign()));
        criteria.add(eq("respElect", cursoId.getCurElect()));
        criteria.add(eq("respCoord", cursoId.getCurCoord()));
        criteria.add(eq("respSecc", cursoId.getCurSecc()));
        criteria.add(eq("respAgno", cursoId.getCurAgno()));
        criteria.add(eq("respSem", cursoId.getCurSem()));
        criteria.add(eq("respProf", rutProf));
        criteria.add(eq("edoTipo", tipo));
        criteria.addOrder(asc("pregNro"));

        return criteria.list();
    }
}
