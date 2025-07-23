/*
 * @(#)PlanLogroPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.PlanLogroRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.AluCarId;
import domain.model.PlanId;
import domain.model.PlanLogro;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.sqlRestriction;

/**
 *
 * @author Ricardo Contreras S.
 */
public class PlanLogroPersistenceImpl extends CrudAbstractDAO<PlanLogro, Long>
        implements PlanLogroRepository {

    /**
     * Method description
     *
     *
     * @param carrera
     * @param mencion
     * @param plan
     * @param correl
     *
     * @return
     */
    @Override
    public PlanLogro find(Integer carrera, Integer mencion, Integer plan, Integer correl) {
        Criteria criteria = getSession().createCriteria(PlanLogro.class);

        criteria.add(eq("id.plalCodCar", carrera));
        criteria.add(eq("id.plalCodMen", mencion));
        criteria.add(eq("id.plalCodPlan", plan));
        criteria.add(eq("id.plalCorrel", correl));

        return (PlanLogro) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PlanLogro> find(AluCarId idA, PlanId idP, Integer logro) {
        Criteria criteria = getSession().createCriteria(PlanLogro.class);

        criteria.add(eq("id.plalCodCar", idP.getPlaCodCar()));
        criteria.add(eq("id.plalCodMen", idP.getPlaCodMen()));
        criteria.add(eq("id.plalCodPlan", idP.getPlaCod()));
        criteria.add(sqlRestriction(
                "exists (select * from expediente_logro where expl_cod_car = plal_cod_car and "
                + "expl_rut=" + idA.getAcaRut() + " and "
                + "expl_cod_car=" + idA.getAcaCodCar() + " and "
                + "expl_agno_ing=" + idA.getAcaAgnoIng() + " and "
                + "expl_sem_ing=" + idA.getAcaSemIng() + " and "
                + "plal_cod_logro="+logro+" and "
                + "expl_correl = plal_correl and "
                + "expl_cod_car = plal_cod_car and "
                + "expl_cod_men = plal_cod_men and "
                + "expl_cod_plan = plal_cod_plan"
                + " )"));

        return criteria.list();
    }    
}
