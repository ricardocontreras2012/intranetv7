/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.persistence;

import domain.repository.LogSolicitudRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.LogSolicitud;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.FetchMode.JOIN;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;

/**
 *
 * @author Ricardo Contreras S.
 */
public class LogSolicitudPersistenceImpl extends CrudAbstractDAO<LogSolicitud, Long>
        implements LogSolicitudRepository {

    /**
     * Method description
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<LogSolicitud> find(Integer solicitud) {
        Criteria criteria = getSession().createCriteria(LogSolicitud.class);

        criteria.setFetchMode("tsolicitud", JOIN);
        criteria.setFetchMode("estadoSolicitud", JOIN);
        criteria.add(eq("logFolio", solicitud));
        criteria.addOrder(asc("logCorrel"));

        return criteria.list();
    }
}

