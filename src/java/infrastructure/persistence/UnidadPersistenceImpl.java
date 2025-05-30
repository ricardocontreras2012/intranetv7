/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.persistence;

import domain.repository.UnidadPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.Unidad;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import static org.hibernate.criterion.Restrictions.eq;
import org.hibernate.type.StandardBasicTypes;

/**
 *
 * @author Ricardo
 */
public class UnidadPersistenceImpl extends CrudAbstractDAO<Unidad, Long> implements UnidadPersistence {

    @Override
    public Unidad findById(Integer uniCod) {
        Criteria criteria = getSession().createCriteria(Unidad.class);

        criteria.add(eq("uniCod", uniCod));

        return (Unidad) criteria.uniqueResult();

    }

    @Override
    public Unidad find(Integer unidad) {
        Criteria criteria = getSession().createCriteria(Unidad.class);

        criteria.createAlias("uniMayor", "uniMayor");
        criteria.add(eq("uniCod", unidad));

        return (Unidad) criteria.uniqueResult();

    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Unidad> findFacultad(Integer rut, String type) {
        
        String sql = "Select * from Unidad where uni_tipo=100 and facultad_pkg.realiza_labor(:rut, :type, uni_cod) >0";

        SQLQuery query = getSession().createSQLQuery(sql).addEntity("Unidad", Unidad.class);
        query.setParameter("rut", rut, StandardBasicTypes.INTEGER);
        query.setParameter("type", type, StandardBasicTypes.STRING);

        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Unidad> findDeptos(Integer rut, String type) {
        
        String sql = "Select * from Unidad where uni_tipo=150 and departamento_pkg.departamento_propio( uni_cod, :type , :rut) >0";

        SQLQuery query = getSession().createSQLQuery(sql).addEntity("Unidad", Unidad.class);
        query.setParameter("rut", rut, StandardBasicTypes.INTEGER);
        query.setParameter("type", type, StandardBasicTypes.STRING);

        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Unidad> findCarreras(Integer rut, String type) {
                      
        String sql = "Select * from Unidad where uni_tipo=160 and carrera_pkg.carrera_propia( uni_cod, :type , :rut) >0";

        SQLQuery query = getSession().createSQLQuery(sql).addEntity("Unidad", Unidad.class);
        query.setParameter("rut", rut, StandardBasicTypes.INTEGER);
        query.setParameter("type", type, StandardBasicTypes.STRING);

        return query.list();
    }
}
