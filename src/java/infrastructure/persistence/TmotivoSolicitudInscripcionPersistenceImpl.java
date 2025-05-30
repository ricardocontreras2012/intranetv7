/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.persistence;

import domain.repository.TmotivoSolicitudInscripcionPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.TmotivoSolicitudInscripcion;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.criterion.Order.asc;

/**
 *
 * @author Ricardo
 */
public class TmotivoSolicitudInscripcionPersistenceImpl extends CrudAbstractDAO<TmotivoSolicitudInscripcion, Long> implements TmotivoSolicitudInscripcionPersistence {

    /**
     * Method description
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TmotivoSolicitudInscripcion> find() {
        Criteria criteria = getSession().createCriteria(TmotivoSolicitudInscripcion.class);

        criteria.addOrder(asc("tmsiCod"));

        return criteria.list();
    }
}

