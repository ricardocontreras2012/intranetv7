/*
 * @(#)SolicitudAttachPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.SolicitudAttachPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.Solicitud;
import domain.model.SolicitudAttach;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.FetchMode.JOIN;
import org.hibernate.Query;
import static org.hibernate.criterion.Restrictions.eq;
import org.hibernate.type.StandardBasicTypes;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class SolicitudAttachPersistenceImpl extends CrudAbstractDAO<SolicitudAttach, Long>
        implements SolicitudAttachPersistence {

    /**
     * Method description
     *
     * @param solicitud
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SolicitudAttach> find(Solicitud solicitud) {
        Criteria criteria = getSession().createCriteria(SolicitudAttach.class);

        criteria.add(eq("solicitud.solFolio", solicitud.getSolFolio()));
        criteria.setFetchMode("tdocumentoSolicitud", JOIN);
        //criteria.addOrder(Order.desc("tdocumentoSolicitud.tdsCod"));

        return criteria.list();
    }


    @Override
    public void addAttach(Integer solicitud, Integer attach, String archivo, Integer tipo) {
        String hql = "insert into Solicitud_Attach (sola_correl_sol,sola_correl_attach, sola_attach_file, sola_tdoc) VALUES "
                + "(:sol,:attach,:archivo,:tipo)";
        Query query = getSession().createSQLQuery(hql);

        query.setParameter("sol", solicitud, StandardBasicTypes.INTEGER);
        query.setParameter("attach", attach, StandardBasicTypes.INTEGER);
        query.setParameter("archivo", archivo, StandardBasicTypes.STRING);
        query.setParameter("tipo", tipo, StandardBasicTypes.INTEGER);

        query.executeUpdate();
    }

    @Override
    public void deleteAttach(Integer solicitud, Integer attach) {
        String hql = "delete from solicitud_attach where sola_correl_sol=:sol AND sola_correl_attach=:attach";

        Query query = getSession().createSQLQuery(hql);

        query.setParameter("sol", solicitud, StandardBasicTypes.INTEGER);
        query.setParameter("attach", attach, StandardBasicTypes.INTEGER);
        query.executeUpdate();
    }
}
