/*
 * @(#)CcalidadPersistenceImpl.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.CcalidadPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.AluCar;
import domain.model.Ccalidad;
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
public final class CcalidadPersistenceImpl extends CrudAbstractDAO<Ccalidad, Long> implements CcalidadPersistence {

    /**
     * Method description
     *
     * @param aluCar
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Ccalidad> find(AluCar aluCar) {
        Criteria criteria = getSession().createCriteria(Ccalidad.class);

        criteria.setFetchMode("tcalidad", JOIN);
        criteria.add(eq("aluCar.id", aluCar.getId()));
        criteria.addOrder(asc("id.ccaAgno"));
        criteria.addOrder(asc("id.ccaSem"));
        criteria.addOrder(asc("id.ccaTipoCal"));

        return criteria.list();
    }

    /**
     * Method description
     *
     * @param agno
     * @param calidad
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Ccalidad> findxCalidad(Integer agno, Integer calidad) {
        Criteria criteria = getSession().createCriteria(Ccalidad.class);

        criteria.setFetchMode("tcalidad", JOIN);
        criteria.setFetchMode("aluCar", JOIN);
        criteria.createAlias("aluCar.alumno", "alumno");
        criteria.add(eq("id.ccaAgno", agno));
        criteria.add(eq("tcalidad.tcaCod", calidad));
        criteria.addOrder(asc("aluCar.id.acaCodCar"));
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
    @Override
    public Ccalidad findxCalidad(AluCar aluCar) {
        Criteria criteria = getSession().createCriteria(Ccalidad.class);

        criteria.add(eq("aluCar.id", aluCar.getId()));
        criteria.add(eq("id.ccaTipoCal", aluCar.getTcalidad().getTcaCod()));

        return (Ccalidad) criteria.uniqueResult();
    }
    
    /**
     * Method description
     *
     * @param aluCar
     * @param calidad
     * @return
     */
    @Override
    public Ccalidad findxCalidad(AluCar aluCar, Integer calidad) {
        Criteria criteria = getSession().createCriteria(Ccalidad.class);

        criteria.add(eq("aluCar.id", aluCar.getId()));
        criteria.add(eq("id.ccaTipoCal", calidad));

       return (Ccalidad) criteria.uniqueResult();
    }
}
