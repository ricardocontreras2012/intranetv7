/*
 * @(#)EvaluacionAlumnoPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.EvaluacionAlumnoPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.Curso;
import domain.model.CursoId;
import domain.model.Evaluacion;
import domain.model.EvaluacionAlumno;
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
public final class EvaluacionAlumnoPersistenceImpl extends CrudAbstractDAO<EvaluacionAlumno, Long>
        implements EvaluacionAlumnoPersistence {

    /**
     * Method description
     *
     * @param evaluacion
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<EvaluacionAlumno> find(Evaluacion evaluacion) {
        Criteria criteria = getSession().createCriteria(EvaluacionAlumno.class);

        criteria.setFetchMode("evaluacion", JOIN);
        criteria.setFetchMode("aluCar", JOIN);
        criteria.setFetchMode("aluCar.alumno", JOIN);
        criteria.setFetchMode("evaluacion.cursoTevaluacion", JOIN);
        criteria.setFetchMode("cursoTevaluacion.tevaluacion", JOIN);
        criteria.createAlias("evaluacion.cursoTevaluacion", "cursoTevaluacion");
        criteria.createAlias("cursoTevaluacion.tevaluacion", "tevaluacion");
        criteria.createAlias("aluCar.alumno", "alumno");
        criteria.add(eq("evaluacion.id.evalTeva", evaluacion.getId().getEvalTeva()));
        criteria.add(eq("evaluacion.id.evalEval", evaluacion.getId().getEvalEval()));
        criteria.add(eq("cursoTevaluacion.curso", evaluacion.getCursoTevaluacion().getCurso()));
        criteria.addOrder(asc("alumno.aluPaterno"));
        criteria.addOrder(asc("alumno.aluMaterno"));
        criteria.addOrder(asc("alumno.aluNombre"));

        return criteria.list();
    }

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<EvaluacionAlumno> getNotas(Integer rut, CursoId id) {

        Criteria criteria = getSession().createCriteria(EvaluacionAlumno.class);

        criteria.setFetchMode("evaluacion", JOIN);
        criteria.setFetchMode("evaluacion.cursoTevaluacion", JOIN);
        criteria.setFetchMode("cursoTevaluacion.tevaluacion", JOIN);
        criteria.createAlias("evaluacion.cursoTevaluacion", "cursoTevaluacion");
        criteria.createAlias("cursoTevaluacion.tevaluacion", "tevaluacion");
        criteria.add(eq("aluCar.id.acaRut", rut));
        criteria.add(eq("cursoTevaluacion.curso.id", id));

        return  criteria.list();
    }

    /**
     * Method description
     *
     * @param curso
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<EvaluacionAlumno> getPlanilla(Curso curso) {
        Criteria criteria = getSession().createCriteria(EvaluacionAlumno.class);

        criteria.setFetchMode("evaluacion", JOIN);
        criteria.setFetchMode("aluCar", JOIN);
        criteria.setFetchMode("aluCar.alumno", JOIN);
        criteria.setFetchMode("evaluacion.cursoTevaluacion", JOIN);
        criteria.setFetchMode("cursoTevaluacion.tevaluacion", JOIN);
        criteria.createAlias("evaluacion.cursoTevaluacion", "cursoTevaluacion");
        criteria.createAlias("cursoTevaluacion.tevaluacion", "tevaluacion");
        criteria.createAlias("aluCar.alumno", "alumno");
        criteria.add(eq("cursoTevaluacion.curso", curso));
        criteria.addOrder(asc("alumno.aluPaterno"));
        criteria.addOrder(asc("alumno.aluMaterno"));
        criteria.addOrder(asc("alumno.aluNombre"));
        criteria.addOrder(asc("evaluacion.id.evalTeva"));
        criteria.addOrder(asc("evaluacion.id.evalEval"));

        return criteria.list();
    }

    /**
     * Method description
     *
     * @param id
     */
    @Override
    public void sincronizaCurso(CursoId id) {
        Query query = getSession().getNamedQuery("SincronizaCursoFunction");

        query.setParameter(0, id.getCurAsign(), StandardBasicTypes.INTEGER);
        query.setParameter(1, id.getCurElect(), StandardBasicTypes.STRING);
        query.setParameter(2, id.getCurCoord(), StandardBasicTypes.STRING);
        query.setParameter(3, id.getCurSecc(), StandardBasicTypes.INTEGER);
        query.setParameter(4, id.getCurAgno(), StandardBasicTypes.INTEGER);
        query.setParameter(5, id.getCurSem(), StandardBasicTypes.INTEGER);
        query.executeUpdate();
        
        query.executeUpdate();

    }
}
