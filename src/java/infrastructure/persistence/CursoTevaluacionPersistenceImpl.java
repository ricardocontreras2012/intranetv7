/*
 * @(#)CursoTevaluacionPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2025 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.CursoTevaluacionPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.Curso;
import domain.model.CursoId;
import domain.model.CursoTevaluacion;
import static java.lang.String.valueOf;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Criteria;
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
public final class CursoTevaluacionPersistenceImpl extends CrudAbstractDAO<CursoTevaluacion, Long>
        implements CursoTevaluacionPersistence {

    /**
     * Method description
     *
     * @param curso
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CursoTevaluacion> find(Curso curso) {
        Criteria criteria = getSession().createCriteria(CursoTevaluacion.class);

        criteria.add(eq("curso.id", curso.getId()));
        criteria.addOrder(asc("tevaluacion.tevalCod"));

        return criteria.list();
    }

    /**
     *
     * @param id
     * @param tipo
     */
    @Override
    public void delete(CursoId id, Integer tipo) {
        String hql = "delete from CursoTevaluacion where "
                + "cteva_asign = :asign and "
                + "cteva_elect = :elect and "
                + "cteva_coord = :coord and "
                + "cteva_secc = :secc and "
                + "cteva_agno = :agno and "
                + "cteva_sem = :sem and "
                + "cteva_teva = :tipo";

        Query query = getSession().createQuery(hql);

        query.setParameter("asign", id.getCurAsign(), StandardBasicTypes.INTEGER);
        query.setParameter("elect", id.getCurElect(), StandardBasicTypes.STRING);
        query.setParameter("coord", id.getCurCoord(), StandardBasicTypes.STRING);
        query.setParameter("secc", id.getCurSecc(), StandardBasicTypes.INTEGER);
        query.setParameter("agno", id.getCurAgno(), StandardBasicTypes.INTEGER);
        query.setParameter("sem", id.getCurSem(), StandardBasicTypes.INTEGER);
        query.setParameter("tipo", tipo, StandardBasicTypes.INTEGER);

        query.executeUpdate();
    }

    /**
     *
     * @param id
     * @param tipo
     */
    @Override
    public void insert(CursoId id, Integer tipo) {

            String hql = "insert into Curso_Tevaluacion (cteva_asign,cteva_elect,cteva_coord,cteva_secc,cteva_agno,cteva_sem,cteva_comp,cteva_teva) VALUES "
                    + "(:asign,:elect,:coord,:secc,:agno,:sem,:comp,:tipo)";
            Query query = getSession().createSQLQuery(hql);

            query.setParameter("asign", id.getCurAsign(), StandardBasicTypes.INTEGER);
            query.setParameter("elect", id.getCurElect(), StandardBasicTypes.STRING);
            query.setParameter("coord", id.getCurCoord(), StandardBasicTypes.STRING);
            query.setParameter("secc", id.getCurSecc(), StandardBasicTypes.INTEGER);
            query.setParameter("agno", id.getCurAgno(), StandardBasicTypes.INTEGER);
            query.setParameter("sem", id.getCurSem(), StandardBasicTypes.INTEGER);
            query.setParameter("comp", id.getCurComp(), StandardBasicTypes.STRING);
            query.setParameter("tipo", tipo, StandardBasicTypes.INTEGER);
            query.executeUpdate();            
    }

    /**
     *
     * @param id
     * @param type
     * @param pond
     */
    @Override
    public void updatePonderacion(CursoId id, Integer type, BigDecimal pond) {
        String hql = "update CursoTevaluacion set cteva_porc=:pond where " + "cteva_asign=:asign AND "
                + "cteva_elect=:elect AND " + "cteva_coord=:coord AND " + "cteva_secc =:secc AND "
                + "cteva_agno =:agno AND " + "cteva_sem  =:sem AND " + "cteva_teva =:type";
        Query query = getSession().createQuery(hql);

        query.setParameter("asign", valueOf(id.getCurAsign()), StandardBasicTypes.STRING);
        query.setParameter("elect", id.getCurElect(), StandardBasicTypes.STRING);
        query.setParameter("coord", id.getCurCoord(), StandardBasicTypes.STRING);
        query.setParameter("secc", valueOf(id.getCurSecc()), StandardBasicTypes.STRING);
        query.setParameter("agno", valueOf(id.getCurAgno()), StandardBasicTypes.STRING);
        query.setParameter("sem", valueOf(id.getCurSem()), StandardBasicTypes.STRING);
        query.setParameter("type", valueOf(type), StandardBasicTypes.STRING);
        query.setParameter("pond", valueOf(pond).replace('.', ','), StandardBasicTypes.STRING);
        query.executeUpdate();
    }
}
