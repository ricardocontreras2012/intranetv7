/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.persistence;

import domain.repository.ProyectoRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.Proyecto;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.FetchMode.JOIN;
import static org.hibernate.criterion.Order.asc;
import org.hibernate.criterion.Restrictions;
import static org.hibernate.criterion.Restrictions.eq;
import org.hibernate.type.IntegerType;
import org.hibernate.type.Type;

/**
 *
 * @author Ricardo
 */
public class ProyectoPersistenceImpl extends CrudAbstractDAO<Proyecto, Long> implements ProyectoRepository {

    @SuppressWarnings("unchecked")
    @Override
    public List<Proyecto> find(Integer rut) {
        Criteria criteria = getSession().createCriteria(Proyecto.class);

        String filter="convenio_pkg.proyecto_propio(proy_cod,:rut)>0";                
        criteria.add(Restrictions.sqlRestriction(filter,
                new Object[]{rut},
                new Type[]{IntegerType.INSTANCE}));
        criteria.addOrder(asc("proyCod"));

        return criteria.list();
    }
    
    @Override
    public Proyecto findProyecto(String  proyecto) {
        Criteria criteria = getSession().createCriteria(Proyecto.class);
        criteria.add(eq("proyCod", proyecto));
        criteria.setFetchMode("jefe", JOIN);

        return (Proyecto)criteria.uniqueResult();
    }        
}
