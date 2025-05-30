/*
 * @(#)ActaPersistenceViewImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.ActaPersistenceView;
import infrastructure.persistence.dao.CrudAbstractDAO;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;
import domain.model.ActaDeptoView;
import domain.model.ActaFacultadView;
import domain.model.ActaMencionView;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class ActaPersistenceViewImpl extends CrudAbstractDAO<ActaMencionView, Long>
        implements ActaPersistenceView {

    /**
     * Method description
     *
     * @param agno
     * @param sem
     * @param tipoCarrera
     * @param especialidad
     * @param regimen
     * @param rut
     * @param userType
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ActaMencionView> findMencion(Integer agno, Integer sem, Integer tipoCarrera, Integer especialidad, String regimen, Integer rut, String userType) {
        Criteria criteria = getSession().createCriteria(ActaMencionView.class);

        criteria.add(eq("id.acalAgno", agno));
        criteria.add(eq("id.acalSem", sem));
        criteria.add(eq("tcrCtip", tipoCarrera));
        criteria.add(eq("espCod", especialidad));
        criteria.add(eq("carRegimen", regimen));
        criteria.add(eq("lreaRut", rut));
        criteria.add(eq("labUser", userType));
        criteria.addOrder(asc("acalAsign"));
        criteria.addOrder(asc("acalElect"));
        criteria.addOrder(asc("acalCoord"));
        criteria.addOrder(asc("acalSecc"));
        criteria.addOrder(asc("id.acalFolio"));

        return criteria.list();
    }

    /**
     * Method description
     *
     * @param agno
     * @param sem
     * @param tipoCarrera
     * @param especialidad
     * @param regimen
     * @param rut
     * @param userType
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ActaDeptoView> findDepto(Integer agno, Integer sem, Integer tipoCarrera, Integer especialidad,
            String regimen, Integer rut, String userType) {
        Criteria criteria = getSession().createCriteria(ActaDeptoView.class);

        criteria.add(eq("id.acalAgno", agno));
        criteria.add(eq("id.acalSem", sem));
        criteria.add(eq("tcrCtip", tipoCarrera));
        criteria.add(eq("espCod", especialidad));
        criteria.add(eq("carRegimen", regimen));
        criteria.add(eq("lreaRut", rut));
        criteria.add(eq("labUser", userType));
        criteria.addOrder(asc("acalAsign"));
        criteria.addOrder(asc("acalElect"));
        criteria.addOrder(asc("acalCoord"));
        criteria.addOrder(asc("acalSecc"));
        criteria.addOrder(asc("id.acalFolio"));

        return criteria.list();
    }

    /**
     * Method description
     *
     * @param agno
     * @param sem
     * @param tipoCarrera
     * @param especialidad
     * @param regimen
     * @param rut
     * @param userType
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ActaFacultadView> findFacultad(Integer agno, Integer sem, Integer tipoCarrera, Integer especialidad, String regimen, Integer rut, String userType) {

        Criteria criteria = getSession().createCriteria(ActaFacultadView.class);

        criteria.add(eq("id.acalAgno", agno));
        criteria.add(eq("id.acalSem", sem));
        criteria.add(eq("tcrCtip", tipoCarrera));
        criteria.add(eq("espCod", especialidad));
        criteria.add(eq("carRegimen", regimen));
        criteria.add(eq("lreaRut", rut));
        criteria.add(eq("labUser", userType));
        criteria.addOrder(asc("acalAsign"));
        criteria.addOrder(asc("acalElect"));
        criteria.addOrder(asc("acalCoord"));
        criteria.addOrder(asc("acalSecc"));
        criteria.addOrder(asc("id.acalFolio"));

        return criteria.list();
    }
}
