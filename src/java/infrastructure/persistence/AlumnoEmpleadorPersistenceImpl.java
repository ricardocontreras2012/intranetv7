/*
 * @(#)AlumnoEmpleadorPersistenceImpl.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.AlumnoEmpleadorPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.AlumnoEmpleador;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.FetchMode.JOIN;
import org.hibernate.Query;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.idEq;
import org.hibernate.type.StandardBasicTypes;

/**
 *
 * @author Alvaro Romero C.
 */
public final class AlumnoEmpleadorPersistenceImpl extends CrudAbstractDAO<AlumnoEmpleador, Long>
        implements AlumnoEmpleadorPersistence {

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<AlumnoEmpleador> find(Integer rut) {
        Criteria criteria = getSession().createCriteria(AlumnoEmpleador.class);

        criteria.add(eq("alumno.aluRut", rut));
        criteria.setFetchMode("empleador", JOIN);

        return criteria.list();
    }

    /**
     * Method description
     *
     *
     * @param rut
     * @param correl
     *
     * @return
     */
    @Override
    public AlumnoEmpleador find(Integer rut, Integer correl) {
        Criteria criteria = getSession().createCriteria(AlumnoEmpleador.class);

        criteria.add(idEq(correl));
        criteria.add(eq("alumno.aluRut", rut));
        criteria.setFetchMode("empleador", JOIN);

        return (AlumnoEmpleador) criteria.uniqueResult();
    }

    /**
     * Method description
     *
     *
     * @param correl
     * @param rutAlumno
     * @param rutEmpleador
     * @param indepActividadEconomica
     * @param desdeAgno
     * @param desdeMes
     * @param hastaAgno
     * @param hastaMes
     */
    @Override
    public void updateLaboral(Integer correl, Integer rutAlumno, Integer rutEmpleador, Integer indepActividadEconomica,
            Integer desdeAgno, Integer desdeMes, Integer hastaAgno, Integer hastaMes) {

        String hql
                = "update AlumnoEmpleador set "
                + "aem_rut_empleador = DECODE(:rutEmpleador,'null',NULL,:rutEmpleador), "
                + "aem_indep_actividad_economica = DECODE(:indepActividadEconomica,'null',NULL,:indepActividadEconomica), "
                + "aem_desde_mes = DECODE(:desdeMes,'null',NULL,:desdeMes), "
                + "aem_desde_agno = DECODE(:desdeAgno,'null',NULL,:desdeAgno), "
                + "aem_hasta_mes = DECODE(:hastaMes,'null',NULL,:hastaMes), "
                + "aem_hasta_agno = DECODE(:hastaAgno,'null',NULL,:hastaAgno) "
                + "where aem_correl = :correl and aem_rut_alumno = :rutAlumno";
        Query query = getSession().createQuery(hql);

        query.setParameter("rutEmpleador", rutEmpleador, StandardBasicTypes.INTEGER);
        query.setParameter("indepActividadEconomica", indepActividadEconomica, StandardBasicTypes.INTEGER);
        query.setParameter("desdeAgno", desdeAgno, StandardBasicTypes.INTEGER);
        query.setParameter("desdeMes", desdeMes, StandardBasicTypes.INTEGER);
        query.setParameter("hastaAgno", hastaAgno, StandardBasicTypes.INTEGER);
        query.setParameter("hastaMes", hastaMes, StandardBasicTypes.INTEGER);
        query.setParameter("correl", correl, StandardBasicTypes.INTEGER);
        query.setParameter("rutAlumno", rutAlumno, StandardBasicTypes.INTEGER);
        query.executeUpdate();
    }

    /**
     * Method description
     *
     *
     * @param correl
     * @param rutAlumno
     * @param rutEmpleador
     * @param indepActividadEconomica
     * @param desdeAgno
     * @param desdeMes
     * @param hastaAgno
     * @param hastaMes
     */
    @Override
    public void createLaboral(Integer correl, Integer rutAlumno, Integer rutEmpleador, Integer indepActividadEconomica,
            Integer desdeAgno, Integer desdeMes, Integer hastaAgno, Integer hastaMes) {

        String sql
                = "insert into Alumno_Empleador "
                + "(aem_correl, aem_rut_alumno, aem_rut_empleador, aem_indep_actividad_economica, aem_desde_mes, aem_desde_agno, aem_hasta_mes, aem_hasta_agno) "
                + "values (:correl,:rutAlumno,:rutEmpleador,:indepActividadEconomica,:desdeMes,:desdeAgno,:hastaMes,:hastaAgno)";
        Query query = getSession().createSQLQuery(sql);

        query.setParameter("correl", correl, StandardBasicTypes.INTEGER);
        query.setParameter("rutAlumno", rutAlumno, StandardBasicTypes.INTEGER);
        query.setParameter("rutEmpleador", rutEmpleador, StandardBasicTypes.INTEGER);
        query.setParameter("indepActividadEconomica", indepActividadEconomica, StandardBasicTypes.INTEGER);
        query.setParameter("desdeMes", desdeMes, StandardBasicTypes.INTEGER);
        query.setParameter("desdeAgno", desdeAgno, StandardBasicTypes.INTEGER);
        query.setParameter("hastaMes", hastaMes, StandardBasicTypes.INTEGER);
        query.setParameter("hastaAgno", hastaAgno, StandardBasicTypes.INTEGER);

        query.executeUpdate();
    }

    /**
     * Method description
     *
     *
     * @param correl
     * @param alumno
     */
    @Override
    public void deleteLaboral(Integer correl, Integer alumno) {
        String hql = "delete from AlumnoEmpleador where flab_correl = :correl and flab_rut_alumno = :alumno";

        Query query = getSession().createQuery(hql);

        query.setParameter("correl", correl, StandardBasicTypes.INTEGER);
        query.setParameter("alumno", alumno, StandardBasicTypes.INTEGER);
        query.executeUpdate();
    }
}
