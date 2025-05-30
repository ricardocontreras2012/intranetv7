/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.persistence;

import domain.repository.CertificacionPersistenceView;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.AluCarId;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.criterion.Restrictions.eq;
import domain.model.CertificacionView;

/**
 *
 * @author Ricardo
 */
public class CertificacionPersistenceViewImpl extends CrudAbstractDAO<CertificacionView, Long>
        implements CertificacionPersistenceView {
    
     /**
     * Method description
     *
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CertificacionView> find(AluCarId id) {
        Criteria criteria = getSession().createCriteria(CertificacionView.class);
        criteria.add(eq("acaRut", id.getAcaRut()));
        criteria.add(eq("acaCodCar", id.getAcaCodCar()));
        criteria.add(eq("acaAgnoIng", id.getAcaAgnoIng()));
        criteria.add(eq("acaSemIng", id.getAcaSemIng()));

        return criteria.list();     
    } 
}
