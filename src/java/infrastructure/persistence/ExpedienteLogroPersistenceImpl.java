/*
 * @(#)ExpedienteLogroPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.model.AluCar;
import domain.repository.ExpedienteLogroPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.ExpedienteLogro;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;
import org.hibernate.type.StandardBasicTypes;
import infrastructure.util.DateUtil;

/**
 *
 * @author Ricardo Contreras S.
 */
public final class ExpedienteLogroPersistenceImpl extends CrudAbstractDAO<ExpedienteLogro, Long>
        implements ExpedienteLogroPersistence {

    /**
     * Method description
     *
     *
     * @param nomina
     * @param agno
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ExpedienteLogro> find(String nomina, Integer agno, Integer logro) {
        Criteria criteria = getSession().createCriteria(ExpedienteLogro.class);

        criteria.createAlias("nomina", "nomina");
        criteria.createAlias("aluCar", "aluCar");
        criteria.createAlias("aluCar.plan", "plan");
        criteria.createAlias("plan.mencion", "mencion");
        criteria.createAlias("mencion.carrera", "carrera");
        criteria.createAlias("carrera.tcarrera", "tcarrera");
        criteria.createAlias("logro.tlogro", "tlogro");
        criteria.createAlias("planLogro.logro", "logro");
        criteria.createAlias("planLogro", "planLogro");
        criteria.createAlias("aluCar.alumno", "alumno");
        criteria.add(eq("nomina.expnNumero", nomina));
        criteria.add(eq("nomina.expnAgno", agno));
        criteria.add(eq("logro.logrCod", logro));
        criteria.addOrder(asc("alumno.aluPaterno"));
        criteria.addOrder(asc("alumno.aluMaterno"));
        criteria.addOrder(asc("alumno.aluNombre"));

        return criteria.list();
    }    

    @SuppressWarnings("unchecked")
    @Override
    public List<ExpedienteLogro> find(AluCar aca) {
        Criteria criteria = getSession().createCriteria(ExpedienteLogro.class);

        criteria.createAlias("logro.tlogro", "tlogro");
        criteria.createAlias("planLogro.logro", "logro");
        criteria.createAlias("planLogro", "planLogro");
        criteria.createAlias("aluCar.alumno", "alumno");
        criteria.add(eq("aluCar.id", aca.getId()));
        criteria.addOrder(asc("planLogro.id.plalCorrel"));

        return criteria.list();
    } 
    
    @SuppressWarnings("unchecked")
    @Override
    public List<ExpedienteLogro> findGeneradas(AluCar aca) {
        Criteria criteria = getSession().createCriteria(ExpedienteLogro.class);

        criteria.createAlias("logro.tlogro", "tlogro");
        criteria.createAlias("planLogro.logro", "logro");
        criteria.createAlias("planLogro", "planLogro");
        criteria.createAlias("aluCar.alumno", "alumno");
        criteria.add(eq("aluCar.id", aca.getId()));
        criteria.add(eq("explEstado", "GE"));
        criteria.addOrder(asc("planLogro.id.plalCorrel"));

        return criteria.list();
    }   
    
    
    @SuppressWarnings("unchecked")
    @Override
    public ExpedienteLogro find(ExpedienteLogro exp) {
        Criteria criteria = getSession().createCriteria(ExpedienteLogro.class);

        criteria.createAlias("logro.tlogro", "tlogro");
        criteria.createAlias("planLogro.logro", "logro");
        criteria.createAlias("planLogro", "planLogro");
        criteria.createAlias("aluCar.alumno", "alumno");
        criteria.add(eq("id", exp.getId()));
        criteria.addOrder(asc("planLogro.id.plalCorrel"));

        return (ExpedienteLogro) criteria.uniqueResult();
    }
    
    @Override
    public ExpedienteLogro findBySolicitud(Integer rut, Integer solicitud) {
        Criteria criteria = getSession().createCriteria(ExpedienteLogro.class);

        criteria.createAlias("logro.tlogro", "tlogro");
        criteria.createAlias("planLogro.logro", "logro");
        criteria.createAlias("planLogro", "planLogro");
        criteria.add(eq("id.explRut", rut));
        criteria.add(eq("explSol", solicitud));
        return (ExpedienteLogro) criteria.uniqueResult();
    }
    
    /**
     * Method description
     *
     *
     * @param rut
     */
    @Override
    public void removeNomina(Integer rut, Integer logro, String nomina, Integer agno) {
        String sql
                = "update expediente_logro set expl_nomina = NULL "
                + "where expl_rut = " + rut + " and exists("
                + "select * from plan_logro, alu_car, logro, expediente_nomina where expl_rut = aca_rut and "
                + "expl_cod_car = aca_cod_car and "
                + "expl_agno_ing = aca_agno_ing and "
                + "expl_sem_ing = aca_sem_ing and "
                + "expl_correl = plal_correl and "
                + "plal_cod_car = aca_cod_car and "
                + "plal_cod_men = aca_cod_men and "
                + "plal_cod_plan = aca_cod_plan and "
                + "plal_cod_logro = logr_cod and "
                + "plal_cod_logro = " + logro + " and "
                + "expl_nomina = expn_correl and "
                + "expn_numero = '" + nomina + "')";
        Query query = getSession().createSQLQuery(sql);

        query.executeUpdate();
    }

    /**
     * Method description
     *
     *
     * @param expediente
     * @param ne
     * @param fe
     * @param rol
     */
    @Override
    public void saveExpediente(ExpedienteLogro expediente, Integer ne, String fe, String rol) {
        String hql
                = "update ExpedienteLogro SET "
                + "expl_nomina =:nom,"
                + "expl_num_exp=:ne,"
                + "expl_fec_exp =:fe,"
                + "expl_rol=:rol "
                + "WHERE expl_rut=:rut AND expl_cod_car=:carrera AND expl_agno_ing=:agno AND expl_sem_ing=:sem AND expl_correl=:correl";
        Query query = getSession().createQuery(hql);

        query.setParameter("rut", expediente.getAluCar().getId().getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter("carrera", expediente.getAluCar().getId().getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter("agno", expediente.getAluCar().getId().getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter("sem", expediente.getAluCar().getId().getAcaSemIng(), StandardBasicTypes.INTEGER);        
        query.setParameter("correl", expediente.getId().getExplCorrel(), StandardBasicTypes.INTEGER);
        query.setParameter("nom", expediente.getNomina().getExpnCorrel(), StandardBasicTypes.INTEGER);
        query.setParameter("ne", ne, StandardBasicTypes.INTEGER);
        query.setParameter("fe", DateUtil.stringToDate(fe), StandardBasicTypes.DATE);
        query.setParameter("rol", rol, StandardBasicTypes.STRING);

        query.executeUpdate();
    }
    
    @Override
    public void saveExpediente(ExpedienteLogro expediente, String rol, Integer resol, Date fecha) {
        String hql
                = "update ExpedienteLogro SET "
                + "expl_rol =:rol,"
                + "expl_num_resol=:resol,"
                + "expl_fec_resol =:fecha, "
                + "expl_estado ='RE' "
                + "WHERE expl_rut=:rut AND expl_cod_car=:carrera AND expl_agno_ing=:agno AND expl_sem_ing=:sem AND expl_correl=:correl";
        Query query = getSession().createQuery(hql);

        query.setParameter("rut", expediente.getAluCar().getId().getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter("carrera", expediente.getAluCar().getId().getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter("agno", expediente.getAluCar().getId().getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter("sem", expediente.getAluCar().getId().getAcaSemIng(), StandardBasicTypes.INTEGER);
        
        query.setParameter("correl", expediente.getId().getExplCorrel(), StandardBasicTypes.INTEGER);
        query.setParameter("rol", rol, StandardBasicTypes.STRING);
        query.setParameter("resol", resol, StandardBasicTypes.INTEGER);
        query.setParameter("fecha", fecha, StandardBasicTypes.DATE);
        
        query.executeUpdate();
    }
    
    @Override
    public void saveExpedienteSolicitud(ExpedienteLogro expediente, Integer solicitud) {

        String hql
                = "update ExpedienteLogro SET "
                + "expl_sol =:solicitud "
                + "WHERE expl_rut=:rut AND expl_cod_car=:carrera AND expl_agno_ing=:agno AND expl_sem_ing=:sem AND expl_correl=:correl";
        Query query = getSession().createQuery(hql);
        
        query.setParameter("rut", expediente.getId().getExplRut(), StandardBasicTypes.INTEGER);
        query.setParameter("carrera", expediente.getId().getExplCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter("agno", expediente.getId().getExplAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter("sem", expediente.getId().getExplSemIng(), StandardBasicTypes.INTEGER);
        query.setParameter("correl", expediente.getId().getExplCorrel(), StandardBasicTypes.INTEGER);
        query.setParameter("solicitud", solicitud, StandardBasicTypes.INTEGER);
        
        query.executeUpdate();
    }

    @Override
    public Date getFechaTramite(Integer rut, Integer carrera, Integer agnoIng, Integer semIng, Integer correl ) {
        String sql
                = "SELECT expl_fec_logro_adm "
                + "FROM expediente_logro, plan_logro WHERE "
                + "plal_cod_car = expl_cod_car and "
                + "plal_cod_men = expl_cod_men and "
                + "plal_cod_plan = expl_cod_plan and "
                + "plal_correl = expl_correl and "
                + "expl_rut="+rut+" AND expl_cod_car="+carrera+" AND "
                + "expl_agno_ing="+agnoIng+" AND expl_sem_ing ="+semIng+" AND "
                + "plal_correl = "+correl;

        Query query = getSession().createSQLQuery(sql);

        return (Date) query.uniqueResult();

    }
}
