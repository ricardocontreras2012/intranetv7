/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.persistence;

import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.AluCarId;
import domain.model.ConvalidacionSolicitud;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.FetchMode.JOIN;
import org.hibernate.Query;
import static org.hibernate.criterion.Restrictions.eq;
import org.hibernate.type.StandardBasicTypes;
import domain.repository.ConvalidacionSolicitudRepository;

/**
 *
 * @author rcontreras
 */
public class ConvalidacionSolicitudPersistenceImpl extends CrudAbstractDAO<ConvalidacionSolicitud, Long> implements ConvalidacionSolicitudRepository {

    @SuppressWarnings("unchecked")
    @Override
    public List<ConvalidacionSolicitud> find(AluCarId id) {
        Criteria criteria = getSession().createCriteria(ConvalidacionSolicitud.class);

        criteria.setFetchMode("aluCar", JOIN);
        criteria.add(eq("aluCar.id", id));

        return criteria.list();
    }

    @Override
    public void setEstado(Integer correl, String estado) {
        String hql = "update ConvalidacionSolicitud set cos_estado = :estado where cos_correl = :correl";

        Query query = getSession().createQuery(hql);

        query.setParameter("correl", correl, StandardBasicTypes.INTEGER);
        query.setParameter("estado", estado, StandardBasicTypes.STRING);

        query.executeUpdate();
    }    
}
