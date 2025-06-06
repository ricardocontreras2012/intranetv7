/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.persistence;

import domain.model.EstadoDocExp;
import domain.model.ExpedienteLogroId;
import domain.repository.EstadoDocExpPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.FetchMode.JOIN;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;

/**
 *
 * @author Ricardo
 */
public class EstadoDocExpPersistenceImpl extends CrudAbstractDAO<EstadoDocExp, Long>
        implements EstadoDocExpPersistence {

    @SuppressWarnings("unchecked")
    @Override
    public List<EstadoDocExp> find(ExpedienteLogroId id) {
        Criteria criteria = getSession().createCriteria(EstadoDocExp.class);

        criteria.add(eq("id", id));
        criteria.setFetchMode("expedienteLogro", JOIN);
        criteria.setFetchMode("expedienteLogro.planLogro", JOIN);
        criteria.setFetchMode("expedienteLogro.planLogro.logro", JOIN);
        criteria.setFetchMode("tDocExpediente", JOIN);
        criteria.addOrder(asc("id.xxxx"));
        criteria.addOrder(asc("id.yyyy"));
        criteria.addOrder(asc("id.zzzz"));

        return criteria.list();
    }
}
