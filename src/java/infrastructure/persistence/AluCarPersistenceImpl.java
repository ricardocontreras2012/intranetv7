/*
 * @(#)AluCarPersistenceImpl.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.AluCarPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.AluCar;
import domain.model.AluCarId;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.FetchMode.JOIN;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.ge;
import static org.hibernate.criterion.Restrictions.le;
import org.hibernate.type.StandardBasicTypes;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class AluCarPersistenceImpl extends CrudAbstractDAO<AluCar, Long> implements AluCarPersistence {

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<AluCar> find(Integer rut) {
        Criteria criteria = getSession().createCriteria(AluCar.class);

        criteria.add(eq("id.acaRut", rut));
        criteria.setFetchMode("tcalidad", JOIN);
        criteria.setFetchMode("tsacademica", JOIN);
        criteria.setFetchMode("plan", JOIN);
        criteria.setFetchMode("plan.mencion", JOIN);
        criteria.setFetchMode("parametroMencion", JOIN);
        criteria.setFetchMode("plan.mencion.carrera", JOIN);
        criteria.setFetchMode("plan.mencion.carrera.tcarrera", JOIN);
        criteria.setFetchMode("plan.mencion.carrera.especialidad", JOIN);
        criteria.setFetchMode("plan.mencion.carrera.tprograma", JOIN);
        criteria.setFetchMode("aaingreso", JOIN);
        criteria.setFetchMode("aaingreso.aaiViaIng", JOIN);
        criteria.addOrder(asc("id.acaAgnoIng"));
        criteria.addOrder(asc("id.acaSemIng"));
        criteria.addOrder(asc("id.acaCodCar"));                

        return criteria.list();         
    }

    /**
     * Method description
     *
     * @param acaId
     * @return
     */
    @Override
    public AluCar find(AluCarId acaId) {
        Criteria criteria = getSession().createCriteria(AluCar.class);

        criteria.add(eq("id", acaId));
        criteria.setFetchMode("tcalidad", JOIN);
        criteria.setFetchMode("tsacademica", JOIN);
        criteria.setFetchMode("plan", JOIN);
        criteria.setFetchMode("plan.mencion", JOIN);
        criteria.setFetchMode("plan.mencion.carrera", JOIN);
        criteria.setFetchMode("plan.mencion.carrera.tcarrera", JOIN);
        criteria.setFetchMode("plan.mencion.carrera.especialidad", JOIN);
        criteria.setFetchMode("plan.mencion.carrera.tprograma", JOIN);
        criteria.setFetchMode("aaingreso", JOIN);
        criteria.setFetchMode("aaingreso.aaiViaIng", JOIN);

        return (AluCar) criteria.uniqueResult();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<AluCar> find(Integer tipoCarrera, Integer especialidad, String regimen) {
        Criteria criteria = getSession().createCriteria(AluCar.class);

        criteria.add(eq("tcarrera.tcrCtip", tipoCarrera));
        criteria.add(eq("especialidad.espCod", especialidad));
        criteria.add(eq("carrera.carRegimen", regimen));
        criteria.setFetchMode("tcalidad", JOIN);
        criteria.setFetchMode("tsacademica", JOIN);
        criteria.setFetchMode("plan", JOIN);
        criteria.setFetchMode("alumno", JOIN);
        criteria.setFetchMode("parametroMencion", JOIN);
        criteria.createAlias("carrera.especialidad", "especialidad");
        criteria.createAlias("carrera.tcarrera", "tcarrera");
        criteria.createAlias("mencion.carrera", "carrera");
        criteria.createAlias("plan.mencion", "mencion");
        criteria.createAlias("alumno", "alumno");
        criteria.createAlias("parametroMencion", "parametroMencion");        
        criteria.addOrder(asc("alumno.aluPaterno"));

        return criteria.list();
    }

    /**
     * Method description
     *
     * @param rut
     * @param tipoCarrera
     * @param especialidad
     * @param regimen
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<AluCar> findActivo(Integer rut, Integer tipoCarrera, Integer especialidad, String regimen) {
        Criteria criteria = getSession().createCriteria(AluCar.class);

        criteria.add(eq("id.acaRut", rut));
        criteria.add(le("tcalidad.tcaCod", 2));
        criteria.add(ge("tsacademica.tsaCod", 100));
        criteria.add(eq("tcarrera.tcrCtip", tipoCarrera));
        criteria.add(eq("especialidad.espCod", especialidad));
        criteria.add(eq("carrera.carRegimen", regimen));
        criteria.setFetchMode("tcalidad", JOIN);
        criteria.setFetchMode("tsacademica", JOIN);
        criteria.setFetchMode("plan", JOIN);
        criteria.createAlias("carrera.especialidad", "especialidad");
        criteria.createAlias("carrera.tcarrera", "tcarrera");
        criteria.createAlias("mencion.carrera", "carrera");
        criteria.createAlias("plan.mencion", "mencion");
        criteria.addOrder(asc("id.acaAgnoIng"));
        criteria.addOrder(asc("id.acaSemIng"));
        criteria.addOrder(asc("id.acaCodCar"));

        return criteria.list();
    }

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<AluCar> findEgresado(Integer rut) {
        Criteria criteria = getSession().createCriteria(AluCar.class);

        criteria.add(eq("id.acaRut", rut));
        criteria.add(ge("tcalidad.tcaCod", 2));
        criteria.setFetchMode("tcalidad", JOIN);
        criteria.setFetchMode("tsacademica", JOIN);
        criteria.setFetchMode("plan", JOIN);
        criteria.setFetchMode("plan.mencion", JOIN);
        criteria.setFetchMode("plan.mencion.carrera", JOIN);
        criteria.setFetchMode("plan.mencion.carrera.tcarrera", JOIN);
        criteria.setFetchMode("plan.mencion.carrera.especialidad", JOIN);
        criteria.setFetchMode("plan.mencion.carrera.tprograma", JOIN);
        criteria.setFetchMode("aaingreso", JOIN);
        criteria.setFetchMode("aaingreso.aaiViaIng", JOIN);
        criteria.addOrder(asc("id.acaAgnoIng"));
        criteria.addOrder(asc("id.acaSemIng"));
        criteria.addOrder(asc("id.acaCodCar"));

        return criteria.list();    
    }

    /**
     *
     * @param acaId
     * @return
     */
    @Override
    public String tipoAlumno(AluCarId acaId) {
        Query query = getSession().getNamedQuery("TipoAlumnoFunction");

        query.setParameter(0, acaId.getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter(1, acaId.getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(2, acaId.getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(3, acaId.getAcaSemIng(), StandardBasicTypes.INTEGER);

        return (String) query.uniqueResult();
    }

    @Override
    public void generaExamenAP(AluCarId id)
     {
        Query query = getSession().getNamedQuery("GeneraExamenAPFunction");

        query.setParameter(0, id.getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter(1, id.getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(2, id.getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(3, id.getAcaSemIng(), StandardBasicTypes.INTEGER);

        query.executeUpdate();
    }
    
    @Override
    public void generaLogros(AluCarId id, Integer mencion, Integer plan)
     {
        Query query = getSession().getNamedQuery("GeneraLogrosFunction");

        query.setParameter(0, id.getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter(1, id.getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(2, id.getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(3, id.getAcaSemIng(), StandardBasicTypes.INTEGER);        
        query.setParameter(4, mencion, StandardBasicTypes.INTEGER);
        query.setParameter(5, plan, StandardBasicTypes.INTEGER);

        query.executeUpdate();
    }

     /**
     * Method description
     *
     *
     * @param aluCar
     * @param rut
     * @param userType
     *
     * @return
     */
    @Override
    public Integer isAlumnoPropio(AluCar aluCar, Integer rut, String userType) {
        Query query = getSession().getNamedQuery("IsAlumnoPropioFunction");

        query.setParameter(0, aluCar.getId().getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(1, aluCar.getAcaCodMen(), StandardBasicTypes.INTEGER);
        query.setParameter(2, rut, StandardBasicTypes.INTEGER);
        query.setParameter(3, userType, StandardBasicTypes.STRING);

        return (Integer) query.uniqueResult();
    }
    
    @Override
    public AluCar getAluCarCertificado(Integer correl)
    {        
        String strQuery = "SELECT alu_car.* FROM alu_car, solicitud_certificado WHERE"
                + " aca_rut = scert_rut AND aca_cod_car = scert_cod_car AND aca_agno_ing = scert_agno_ing AND aca_sem_ing = scert_sem_ing AND"
                + " scert_correl= ?";

        SQLQuery query = getSession().createSQLQuery(strQuery).addEntity("AluCar", AluCar.class);
        query.setParameter(0, correl, StandardBasicTypes.INTEGER);
        
        return (AluCar)query.uniqueResult();
    }
    
    @Override
    public Float getPromedioEgreso(AluCarId id, Integer mencion,  Integer plan)
     { 
        Query query = getSession().getNamedQuery("GetPromedioEgresoFunction");

        query.setParameter(0, id.getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter(1, id.getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(2, id.getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(3, id.getAcaSemIng(), StandardBasicTypes.INTEGER);
        query.setParameter(4, mencion, StandardBasicTypes.INTEGER);
        query.setParameter(5, plan, StandardBasicTypes.INTEGER);  
        
        return (Float) query.uniqueResult();
    }
    
    @Override
    public Float getPromedioRanking(AluCarId id, Integer mencion,  Integer plan)
     { 
        Query query = getSession().getNamedQuery("GetPromedioRankingFunction");

        query.setParameter(0, id.getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter(1, id.getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(2, id.getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(3, id.getAcaSemIng(), StandardBasicTypes.INTEGER);
        query.setParameter(4, mencion, StandardBasicTypes.INTEGER);
        query.setParameter(5, plan, StandardBasicTypes.INTEGER);  
        
        return (Float) query.uniqueResult();
    }
}
