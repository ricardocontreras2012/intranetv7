/*
 * @(#)ReservaSalaPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.ReservaSalaPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.ReservaSala;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import static org.hibernate.criterion.Restrictions.eq;
import org.hibernate.type.StandardBasicTypes;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class ReservaSalaPersistenceImpl extends CrudAbstractDAO<ReservaSala, Long>
        implements ReservaSalaPersistence {

    /**
     * Method description
     *
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public ReservaSala find(Integer reserva) {
        Criteria criteria = getSession().createCriteria(ReservaSala.class);
        criteria.add(eq("rsalCorrel", reserva));
        return (ReservaSala) criteria.uniqueResult();
    }

    @Override
    public void reservarSala(String sala, String dia, Integer modulo, String inicio, String termino, String motivo, Integer rut) {
        Query query = getSession().getNamedQuery("ReservarSalaFunction");

        query.setParameter(0, sala, StandardBasicTypes.STRING);
        query.setParameter(1, dia, StandardBasicTypes.STRING);
        query.setParameter(2, modulo, StandardBasicTypes.INTEGER);
        query.setParameter(3, inicio, StandardBasicTypes.STRING);
        query.setParameter(4, termino, StandardBasicTypes.STRING);
        query.setParameter(5, motivo, StandardBasicTypes.STRING);
        query.setParameter(6, rut, StandardBasicTypes.INTEGER);

        query.executeUpdate();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ReservaSala> findReservas(Integer rut) {
        Criteria criteria = getSession().createCriteria(ReservaSala.class);
        
        criteria.createAlias("administrativo", "administrativo");
        criteria.add(eq("administrativo.admRut", rut));
        criteria.addOrder(Order.desc("rsalCorrel"));
        return criteria.list();
    }
    
    @Override
    public void remove(Integer correl) {
        String hql = "delete from ReservaSala where rsal_correl= :correl";
        Query query = getSession().createQuery(hql);

        query.setParameter("correl", correl, StandardBasicTypes.INTEGER);
        query.executeUpdate();
    }
}
