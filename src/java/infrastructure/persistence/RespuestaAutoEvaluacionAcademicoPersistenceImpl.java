/*
 * @(#)RespuestaAutoEvaluacionAcademicoPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.RespuestaAutoEvaluacionAcademicoPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.CursoProfesor;
import domain.model.RespuestaAutoEvaluacionAcademico;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.FetchMode.JOIN;
import org.hibernate.Query;
import static org.hibernate.criterion.Restrictions.sqlRestriction;
import static org.hibernate.sql.JoinType.LEFT_OUTER_JOIN;
import org.hibernate.type.StandardBasicTypes;


/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 05/09/2013
 */
public final class RespuestaAutoEvaluacionAcademicoPersistenceImpl
        extends CrudAbstractDAO<RespuestaAutoEvaluacionAcademico, Long>
        implements RespuestaAutoEvaluacionAcademicoPersistence {

    /**
     * Method description
     *
     *
     * @param rut
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CursoProfesor> getCursos(Integer rut) {              
        Criteria criteria = getSession().createCriteria(CursoProfesor.class);
        criteria.setFetchMode("curso", JOIN);
        criteria.setFetchMode("curso.asignatura", JOIN);
        criteria.createCriteria("curso.electivo", LEFT_OUTER_JOIN);
       
        String sqlFilter
                = "exists (SELECT * "
                + "FROM RESPUESTA_AUTOEVAL_ACADEMICO WHERE RAA_CORREL is NULL AND "
                + "RAA_RUT=" + rut+" AND "
                + "raa_asign = cur_asign and "
                + "raa_elect = cur_elect and "
                + "raa_coord = cur_coord and "
                + "raa_secc = cur_secc and "
                + "raa_agno = cur_agno and "
                + "raa_sem = cur_sem )";
        criteria.add(sqlRestriction(sqlFilter));
      
        return criteria.list();      
    }

    /**
     * Method description
     *
     * @param preg
     * @param resp
     * @param correl
     */
    @Override
    public void doSave(CursoProfesor cursoProfesor, Integer preg, Integer resp, Integer correl) {
        String hqlUpdate = "update RespuestaAutoEvaluacionAcademico set id.raaResp=:newResp , raaCorrel=:newCorrel where"
                + " id.raaRut = :oldRut AND" + " id.raaAsign = :oldAsign AND"
                + " id.raaElect = :oldElect AND" + " id.raaCoord = :oldCoord AND"
                + " id.raaSecc = :oldSecc AND" + " id.raaAgno = :oldAgno AND" + " id.raaSem = :oldSem AND"
                + " id.raaPreg = :oldPreg";

        getSession().createQuery(hqlUpdate).setInteger("newResp", resp).setInteger("newCorrel", correl).setInteger("oldRut",
                cursoProfesor.getId().getCproRut()).setInteger("oldAsign",
                cursoProfesor.getId().getCproAsign()).setString("oldElect",
                cursoProfesor.getId().getCproElect()).setString("oldCoord",
                cursoProfesor.getId().getCproCoord()).setInteger("oldSecc",
                cursoProfesor.getId().getCproSecc()).setInteger("oldAgno",
                cursoProfesor.getId().getCproAgno()).setInteger("oldSem",
                cursoProfesor.getId().getCproSem()).setInteger("oldPreg",
                preg).executeUpdate();
    }

    /**
     * Method description
     *
     *
     * @param rut
     */
    @Override
    public void remove(Integer rut) {
        String sqlUpdate = "update respuesta_autoeval_academico set raa_rut= null where raa_rut=:rut";

        Query query = getSession().createSQLQuery(sqlUpdate);

        query.setParameter("rut", rut, StandardBasicTypes.INTEGER);

        query.executeUpdate();
    }
}
