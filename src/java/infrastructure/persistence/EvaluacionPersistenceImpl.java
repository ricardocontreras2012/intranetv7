/*
 * @(#)EvaluacionPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.EvaluacionPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.Curso;
import domain.model.CursoId;
import domain.model.Evaluacion;
import static java.lang.String.valueOf;
import java.math.BigDecimal;
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
public final class EvaluacionPersistenceImpl extends CrudAbstractDAO<Evaluacion, Long>
        implements EvaluacionPersistence {

    /**
     * Method description
     *
     * @param curso
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Evaluacion> find(Curso curso) {
        Criteria criteria = getSession().createCriteria(Evaluacion.class);

        criteria.setFetchMode("cursoTevaluacion", JOIN);
        criteria.setFetchMode("cursoTevaluacion.curso", JOIN);
        criteria.setFetchMode("cursoTevaluacion.tevaluacion", JOIN);
        criteria.createAlias("cursoTevaluacion", "cursoTevaluacion");
        criteria.add(eq("cursoTevaluacion.curso", curso));
        criteria.addOrder(asc("cursoTevaluacion.tevaluacion.tevalCod"));
        criteria.addOrder(asc("id.evalEval"));

        return criteria.list();
    }

    /**
     * Method description
     *
     * @param cursoId
     * @param type
     * @param eval
     */
    @Override
    public void deleteEvaluacion(CursoId cursoId, Integer type, Integer eval) {
        String hql = "delete from Evaluacion where " + "eval_asign=:asign AND " + "eval_elect=:elect AND "
                + "eval_coord=:coord AND " + "eval_secc =:secc AND " + "eval_agno =:agno AND "
                + "eval_sem  =:sem AND eval_comp=:comp AND " + "eval_teva =:type AND " + "eval_eval = :eval";
        Query query = getSession().createQuery(hql);

        query.setParameter("asign", valueOf(cursoId.getCurAsign()), StandardBasicTypes.STRING);
        query.setParameter("elect", cursoId.getCurElect(), StandardBasicTypes.STRING);
        query.setParameter("coord", cursoId.getCurCoord(), StandardBasicTypes.STRING);
        query.setParameter("secc", valueOf(cursoId.getCurSecc()), StandardBasicTypes.STRING);
        query.setParameter("agno", valueOf(cursoId.getCurAgno()), StandardBasicTypes.STRING);
        query.setParameter("sem", valueOf(cursoId.getCurSem()), StandardBasicTypes.STRING);
        query.setParameter("comp", cursoId.getCurComp(), StandardBasicTypes.STRING);
        query.setParameter("type", valueOf(type), StandardBasicTypes.STRING);
        query.setParameter("eval", valueOf(eval), StandardBasicTypes.STRING);
        query.executeUpdate();
    }

    /**
     * Method description
     *
     * @param cursoId
     * @param type
     * @param eval
     * @param porc
     */
    @Override
    public void insertEvaluacion(CursoId cursoId, Integer type, Integer eval, Integer porc) {
        String sql = "insert into evaluacion(" + "eval_asign, eval_elect, eval_coord, eval_secc, eval_agno, eval_sem, eval_comp,"
                + "eval_teva, eval_eval, eval_porc) VALUES("
                + ":asign, :elect, :coord, :secc, :agno, :sem, :comp,:tipo, :eval, :porc)";
        Query query = getSession().createSQLQuery(sql);

        query.setParameter("asign", valueOf(cursoId.getCurAsign()), StandardBasicTypes.STRING);
        query.setParameter("elect", cursoId.getCurElect(), StandardBasicTypes.STRING);
        query.setParameter("coord", cursoId.getCurCoord(), StandardBasicTypes.STRING);
        query.setParameter("secc", valueOf(cursoId.getCurSecc()), StandardBasicTypes.STRING);
        query.setParameter("agno", valueOf(cursoId.getCurAgno()), StandardBasicTypes.STRING);
        query.setParameter("sem", valueOf(cursoId.getCurSem()), StandardBasicTypes.STRING);
        query.setParameter("comp", cursoId.getCurComp(), StandardBasicTypes.STRING);
        query.setParameter("tipo", valueOf(type), StandardBasicTypes.STRING);
        query.setParameter("eval", valueOf(eval), StandardBasicTypes.STRING);
        query.setParameter("porc", valueOf(porc), StandardBasicTypes.STRING);
        query.executeUpdate();
    }

    /**
     * Method description
     *
     * @param cursoId
     * @param type
     * @param eval
     * @param pond
     */
    @Override
    public void updatePonderacion(CursoId cursoId, Integer type, Integer eval, BigDecimal pond) {
        String hql = "update Evaluacion set eval_porc=:pond where " + "eval_asign=:asign AND "
                + "eval_elect=:elect AND " + "eval_coord=:coord AND " + "eval_secc =:secc AND "
                + "eval_agno =:agno AND " + "eval_sem  =:sem AND eval_comp= :comp AND " + "eval_teva =:type AND " + "eval_eval =:eval";
        Query query = getSession().createQuery(hql);

        query.setParameter("asign", valueOf(cursoId.getCurAsign()), StandardBasicTypes.STRING);
        query.setParameter("elect", cursoId.getCurElect(), StandardBasicTypes.STRING);
        query.setParameter("coord", cursoId.getCurCoord(), StandardBasicTypes.STRING);
        query.setParameter("secc", valueOf(cursoId.getCurSecc()), StandardBasicTypes.STRING);
        query.setParameter("agno", valueOf(cursoId.getCurAgno()), StandardBasicTypes.STRING);
        query.setParameter("sem", valueOf(cursoId.getCurSem()), StandardBasicTypes.STRING);
        query.setParameter("comp", cursoId.getCurComp(), StandardBasicTypes.STRING);
        query.setParameter("type", valueOf(type), StandardBasicTypes.STRING);
        query.setParameter("eval", valueOf(eval), StandardBasicTypes.STRING);        
        query.setParameter("pond", valueOf(pond).replace('.', ','), StandardBasicTypes.STRING);
        query.executeUpdate();
    }

    /**
     * Method description
     *
     * @param evaluacion
     */
    @Override
    public void setStatusConNota(Evaluacion evaluacion) {
        String hql = "update Evaluacion set eval_status='CN' where " + "eval_asign=:asign AND "
                + "eval_elect=:elect AND " + "eval_coord=:coord AND " + "eval_secc =:secc AND "
                + "eval_agno =:agno AND " + "eval_sem  =:sem AND " + "eval_teva =:type AND "
                + "eval_eval = :correl AND eval_status='SN'";
        Query query = getSession().createQuery(hql);

        query.setParameter("asign", valueOf(evaluacion.getId().getEvalAsign()), StandardBasicTypes.STRING);
        query.setParameter("elect", evaluacion.getId().getEvalElect(), StandardBasicTypes.STRING);
        query.setParameter("coord", evaluacion.getId().getEvalCoord(), StandardBasicTypes.STRING);
        query.setParameter("secc", valueOf(evaluacion.getId().getEvalSecc()), StandardBasicTypes.STRING);
        query.setParameter("agno", valueOf(evaluacion.getId().getEvalAgno()), StandardBasicTypes.STRING);
        query.setParameter("sem", valueOf(evaluacion.getId().getEvalSem()), StandardBasicTypes.STRING);
        query.setParameter("type", valueOf(evaluacion.getId().getEvalTeva()), StandardBasicTypes.STRING);
        query.setParameter("correl", valueOf(evaluacion.getId().getEvalEval()), StandardBasicTypes.STRING);
        query.executeUpdate();
    }
}
