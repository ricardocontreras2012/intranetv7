/*
 * @(#)ReporteClasePersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.ReporteClaseRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.CursoId;
import domain.model.ReporteClase;
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
public final class ReporteClasePersistenceImpl extends CrudAbstractDAO<ReporteClase, Long>
        implements ReporteClaseRepository {

    /**
     * Method description
     *
     * @param cursoId
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ReporteClase> find(CursoId cursoId) {
        Criteria criteria = getSession().createCriteria(ReporteClase.class);

        criteria.add(eq("curso.id", cursoId));
        criteria.addOrder(asc("id.rclaFecClase"));
        criteria.addOrder(asc("id.rclaDia"));
        criteria.addOrder(asc("id.rclaModulo"));

        return criteria.list();
    }

    /**
     * Method description
     *
     * @param reporte
     * @return
     */
    @Override
    public boolean exists(ReporteClase reporte) {
        Criteria criteria = getSession().createCriteria(ReporteClase.class);

        criteria.add(eq("id", reporte.getId()));

        ReporteClase reporteClase = (ReporteClase) criteria.uniqueResult();

        return reporteClase != null;
    }
}
