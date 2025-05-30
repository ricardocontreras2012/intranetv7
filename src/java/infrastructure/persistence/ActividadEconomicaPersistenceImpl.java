/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.persistence;

import domain.repository.ActividadEconomicaPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.ActividadEconomica;
import java.util.List;
import org.hibernate.Criteria;

/**
 *
 * @author Virtual
 */
public final class ActividadEconomicaPersistenceImpl extends CrudAbstractDAO<ActividadEconomica, Long> implements ActividadEconomicaPersistence {

    /**
     * Method description
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ActividadEconomica> find() {
        Criteria criteria = getSession().createCriteria(ActividadEconomica.class);

        //criteria.setFetchMode("region", FetchMode.JOIN);
        //criteria.addOrder(Order.asc("comNom"));

        return criteria.list();
    }
}
