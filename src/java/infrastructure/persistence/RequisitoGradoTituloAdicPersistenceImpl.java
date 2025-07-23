/*
 * @(#)RequisitoGradoTituloAdicPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.RequisitoGradoTituloAdicRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.AluCar;
import domain.model.RequisitoLogroAdicional;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.criterion.CriteriaSpecification.DISTINCT_ROOT_ENTITY;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class RequisitoGradoTituloAdicPersistenceImpl extends CrudAbstractDAO<RequisitoLogroAdicional, Long>
        implements RequisitoGradoTituloAdicRepository {

    /**
     * Method description
     *
     * @param aluCar
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<RequisitoLogroAdicional> find(AluCar aluCar) {
        Criteria criteria = getSession().createCriteria(RequisitoLogroAdicional.class);
        
        criteria.createAlias("planLogro.logro", "logro");
        criteria.createAlias("planLogro.plan", "plan");
        criteria.createAlias("planLogro", "planLogro");
        criteria.createAlias("trequisitoLogroAdicional", "trequisitoLogroAdicional");        
        criteria.add(eq("plan.id.plaCodCar", aluCar.getId().getAcaCodCar()));
        criteria.add(eq("plan.id.plaCodMen", aluCar.getAcaCodMen()));
        criteria.add(eq("plan.id.plaCod", aluCar.getAcaCodPlan()));
        criteria.addOrder(asc("trequisitoLogroAdicional.trlaCod"));

        return criteria.list();
    }

    /**
     * Method description
     *
     * @param tipoCarrera
     * @param especialidad
     * @param regimen
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<RequisitoLogroAdicional> find(Integer tipoCarrera, Integer especialidad, String regimen) {
        Criteria criteria = getSession().createCriteria(RequisitoLogroAdicional.class);

        criteria.createAlias("plan", "plan");
        criteria.createAlias("plan.mencion", "mencion");
        criteria.createAlias("mencion.carrera", "carrera");
        criteria.createAlias("carrera.tcarrera", "tcarrera");
        criteria.createAlias("carrera.especialidad", "especialidad");
        criteria.createAlias("trequisitoLogroAdicional", "trequisitoLogroAdicional");
        criteria.add(eq("tcarrera.tcrCtip", tipoCarrera));
        criteria.add(eq("especialidad.espCod", especialidad));
        criteria.add(eq("carrera.carRegimen", regimen));
        criteria.add(eq("plan.plaVigente", 'S'));
        criteria.setResultTransformer(DISTINCT_ROOT_ENTITY);

        return criteria.list();
    }
}
