/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.persistence;

import domain.repository.SolicitudCertificadoCarritoPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.SolicitudCertificadoCarrito;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.FetchMode.JOIN;
import org.hibernate.criterion.Order;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.sqlRestriction;

/**
 *
 * @author Ricardo
 */
public class SolicitudCertificadoCarritoPersistenceImpl extends CrudAbstractDAO<SolicitudCertificadoCarrito, Long>
        implements SolicitudCertificadoCarritoPersistence {

    @SuppressWarnings("unchecked")
    @Override
    public List<SolicitudCertificadoCarrito> find(String userType, Integer rut) {
        Criteria criteria = getSession().createCriteria(SolicitudCertificadoCarrito.class);
                
        String sqlFilter = "perfil_intranet_pkg.mencion_propia( aca_cod_car, aca_cod_men, '"+userType+"',"+rut+")>0";
        
        criteria.setFetchMode("tcertificado", JOIN);
        criteria.setFetchMode("solicitud", JOIN);
        criteria.setFetchMode("solicitud.aluCar", JOIN);
        criteria.setFetchMode("solicitud.aluCar.alumno", JOIN);
        criteria.add(eq("sccEstado", "GL"));
        criteria.addOrder(Order.asc("id.sccSolicitud"));
        criteria.addOrder(Order.asc("id.sccOrd"));        
        criteria.add(sqlRestriction(sqlFilter));

        return criteria.list();
    }
}
