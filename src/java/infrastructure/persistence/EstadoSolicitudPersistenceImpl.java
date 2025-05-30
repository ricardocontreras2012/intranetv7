/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.persistence;

import domain.repository.EstadoSolicitudPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.EstadoSolicitud;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.criterion.Order.asc;

/**
 *
 * @author Ricardo Contreras S.
 */
public class EstadoSolicitudPersistenceImpl extends CrudAbstractDAO<EstadoSolicitud, Long> implements EstadoSolicitudPersistence {

    /**
     * Method description
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<EstadoSolicitud> find() {
        Criteria criteria = getSession().createCriteria(EstadoSolicitud.class);
        
        criteria.addOrder(asc("esolCod"));
        
        return criteria.list();
    }
}