/*
 * @(#)AsistenciaAlumnoPersistenceImpl.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.persistence;

import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.AsistenciaAlumno;
import domain.model.Curso;
import static java.lang.String.valueOf;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;
import org.hibernate.type.StandardBasicTypes;
import domain.repository.AsistenciaAlumnoRepository;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class AsistenciaAlumnoPersistenceImpl extends CrudAbstractDAO<AsistenciaAlumno, Long>
        implements AsistenciaAlumnoRepository {

    /**
     * Method description
     *
     * @param curso
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<AsistenciaAlumno> find(Curso curso) {
        Criteria criteria = getSession().createCriteria(AsistenciaAlumno.class);

        criteria.add(eq("curso", curso));
        criteria.addOrder(asc("asaFecha"));

        return criteria.list();      
    }

    /**
     * Method description
     *
     * @param correl
     */
    @Override
    public void delete(Integer correl) {
        String hql = "delete from AsistenciaAlumno where asa_correl= :correl";
        Query query = getSession().createQuery(hql);

        query.setParameter("correl", valueOf(correl), StandardBasicTypes.STRING);
        query.executeUpdate();
    }

    /**
     * Method description
     *
     * @param correl
     * @param fecha
     */
    @Override
    public void updateFecha(Integer correl, Date fecha) {
        String hql = "update AsistenciaAlumno set asa_fecha = :fecha where asa_correl = :correl";
        Query query = getSession().createQuery(hql);

        query.setParameter("correl", correl, StandardBasicTypes.INTEGER);
        query.setParameter("fecha", fecha, StandardBasicTypes.DATE);
        query.executeUpdate();
    }
}
