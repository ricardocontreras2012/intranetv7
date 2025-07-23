/*
 * @(#)SacarreraPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.SacarreraRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.AluCar;
import domain.model.AluCarId;
import domain.model.Sacarrera;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.FetchMode.JOIN;
import org.hibernate.Query;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;
import org.hibernate.type.StandardBasicTypes;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class SacarreraPersistenceImpl extends CrudAbstractDAO<Sacarrera, Long> implements SacarreraRepository {

    /**
     * Method description
     *
     * @param aluCar
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Sacarrera> find(AluCar aluCar) {
        Criteria criteria = getSession().createCriteria(Sacarrera.class);

        criteria.setFetchMode("tsacademica", JOIN);
        criteria.add(eq("aluCar.id", aluCar.getId()));
        criteria.addOrder(asc("id.sacCorrel"));

        return criteria.list();
    }    

    @Override
    public void retiroSinExp(AluCarId aluCarId, Integer agno, Integer sem, Integer docto) {
        Query query = getSession().getNamedQuery("RetiroSinExpFunction");

        query.setParameter(0, aluCarId.getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter(1, aluCarId.getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(2, aluCarId.getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(3, aluCarId.getAcaSemIng(), StandardBasicTypes.INTEGER);
        query.setParameter(4, agno, StandardBasicTypes.INTEGER);
        query.setParameter(5, sem, StandardBasicTypes.INTEGER);
        query.setParameter(6, docto, StandardBasicTypes.INTEGER);

        query.executeUpdate();
    }
    
    @Override
    public void retiroConExp(AluCarId aluCarId, Integer agno, Integer sem, Integer docto, String fecha) {
        Query query = getSession().getNamedQuery("RetiroConExpFunction");
               
        query.setParameter(0, aluCarId.getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter(1, aluCarId.getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(2, aluCarId.getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(3, aluCarId.getAcaSemIng(), StandardBasicTypes.INTEGER);
        query.setParameter(4, agno, StandardBasicTypes.INTEGER);
        query.setParameter(5, sem, StandardBasicTypes.INTEGER);
        query.setParameter(6, docto, StandardBasicTypes.INTEGER);
        query.setParameter(7, fecha, StandardBasicTypes.STRING);

        query.executeUpdate();
    }
    

    @Override
    public void prorroga(AluCarId aluCarId, Integer agno, Integer sem, Integer docto) {
        Query query = getSession().getNamedQuery("ProrrogaFunction");

        query.setParameter(0, aluCarId.getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter(1, aluCarId.getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(2, aluCarId.getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(3, aluCarId.getAcaSemIng(), StandardBasicTypes.INTEGER);
        query.setParameter(4, agno, StandardBasicTypes.INTEGER);
        query.setParameter(5, sem, StandardBasicTypes.INTEGER);
        query.setParameter(6, docto, StandardBasicTypes.INTEGER);

        query.executeUpdate();
    }
    
    @Override
    public void renuncia(AluCarId aluCarId, Integer agno, Integer sem, String motivo, Integer docto) {
        Query query = getSession().getNamedQuery("RenunciaFunction");

        query.setParameter(0, aluCarId.getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter(1, aluCarId.getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(2, aluCarId.getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(3, aluCarId.getAcaSemIng(), StandardBasicTypes.INTEGER);
        query.setParameter(4, agno, StandardBasicTypes.INTEGER);
        query.setParameter(5, sem, StandardBasicTypes.INTEGER);
        query.setParameter(6, motivo, StandardBasicTypes.STRING);
        query.setParameter(7, docto, StandardBasicTypes.INTEGER);

        query.executeUpdate();
    }
    
    @Override
    public void reincorporacionEliminacion(AluCarId aluCarId, Integer agno, Integer sem, Integer docto, String fecha, String obs){
        Query query = getSession().getNamedQuery("ReincorporacionEliminacionFunction");    
        
        query.setParameter(0, aluCarId.getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter(1, aluCarId.getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(2, aluCarId.getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(3, aluCarId.getAcaSemIng(), StandardBasicTypes.INTEGER);
        query.setParameter(4, agno, StandardBasicTypes.INTEGER);
        query.setParameter(5, sem, StandardBasicTypes.INTEGER);
        query.setParameter(6, docto, StandardBasicTypes.INTEGER);
        query.setParameter(7, fecha, StandardBasicTypes.STRING);
        query.setParameter(8, obs, StandardBasicTypes.STRING);

        query.executeUpdate();
    }
    
    @Override
    public void reincorporacionAbandono(AluCarId aluCarId, Integer agno, Integer sem, Integer docto, String fecha, String obs){
        Query query = getSession().getNamedQuery("ReincorporacionAbandonoFunction");    
        
        query.setParameter(0, aluCarId.getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter(1, aluCarId.getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(2, aluCarId.getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(3, aluCarId.getAcaSemIng(), StandardBasicTypes.INTEGER);
        query.setParameter(4, agno, StandardBasicTypes.INTEGER);
        query.setParameter(5, sem, StandardBasicTypes.INTEGER);
        query.setParameter(6, docto, StandardBasicTypes.INTEGER);
        query.setParameter(7, fecha, StandardBasicTypes.STRING);
        query.setParameter(8, obs, StandardBasicTypes.STRING);

        query.executeUpdate();
    }
    
    @Override
    public void reincorporacionNoTitulacion(AluCarId aluCarId, Integer agno, Integer sem, Integer docto, String fecha, String obs){
        Query query = getSession().getNamedQuery("ReincorporacionNoTitulacionFunction");    
        
        query.setParameter(0, aluCarId.getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter(1, aluCarId.getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(2, aluCarId.getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(3, aluCarId.getAcaSemIng(), StandardBasicTypes.INTEGER);
        query.setParameter(4, agno, StandardBasicTypes.INTEGER);
        query.setParameter(5, sem, StandardBasicTypes.INTEGER);
        query.setParameter(6, docto, StandardBasicTypes.INTEGER);
        query.setParameter(7, fecha, StandardBasicTypes.STRING);
        query.setParameter(8, obs, StandardBasicTypes.STRING);

        query.executeUpdate();
    }
}
