/*
 * @(#)AsistenciaAlumnoNominaPersistenceImpl.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.AsistenciaAlumnoNominaPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.AluCarId;
import domain.model.AsistenciaAlumnoNomina;
import domain.model.Curso;
import static java.lang.String.valueOf;
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
public final class AsistenciaAlumnoNominaPersistenceImpl extends CrudAbstractDAO<AsistenciaAlumnoNomina, Long>
        implements AsistenciaAlumnoNominaPersistence {

    /**
     * Method description
     *
     * @param correl
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<AsistenciaAlumnoNomina> find(Integer correl) {
        Criteria criteria = getSession().createCriteria(AsistenciaAlumnoNomina.class);

        criteria.createAlias("aluCar", "aluCar");
        criteria.createAlias("aluCar.alumno", "alumno");
        criteria.add(eq("id.aanCorrel", correl));
        criteria.addOrder(asc("alumno.aluPaterno"));
        criteria.addOrder(asc("alumno.aluMaterno"));
        criteria.addOrder(asc("alumno.aluNombre"));

        return criteria.list();
    }

    /**
     * Method description
     *
     * @param correl
     */
    @Override
    public void delete(Integer correl) {
        String hql = "delete from AsistenciaAlumnoNomina where aan_correl= :correl";
        Query query = getSession().createQuery(hql);

        query.setParameter("correl", valueOf(correl), StandardBasicTypes.STRING);
        query.executeUpdate();
    }

    /**
     * Method description
     *
     * @param aluCarId
     * @param correl
     * @param asistio
     */
    @Override
    public void updateNomina(AluCarId aluCarId, Integer correl, Integer asistio) {
        String hql = "update AsistenciaAlumnoNomina set aan_asistio =:asistio WHERE " + "aan_rut =:rut AND "
                + "aan_cod_car =:carrera AND " + "aan_agno_ing=:agno_ingreso AND "
                + "aan_sem_ing=:sem_ingreso AND " + "aan_correl=:correl";
        Query query = getSession().createQuery(hql);

        query.setParameter("rut", aluCarId.getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter("carrera", aluCarId.getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter("agno_ingreso", aluCarId.getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter("sem_ingreso", aluCarId.getAcaSemIng(), StandardBasicTypes.INTEGER);
        query.setParameter("correl", correl, StandardBasicTypes.INTEGER);
        query.setParameter("asistio", asistio, StandardBasicTypes.INTEGER);
        query.executeUpdate();
    }

    /**
     * Method description
     *
     * @param curso
     * @param rut
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<AsistenciaAlumnoNomina> find(Curso curso, Integer rut) {
        Criteria criteria = getSession().createCriteria(AsistenciaAlumnoNomina.class);

        criteria.createAlias("asistenciaAlumno", "asistenciaAlumno");
        criteria.add(eq("asistenciaAlumno.curso", curso));
        criteria.add(eq("id.aanRut", rut));
        criteria.addOrder(asc("asistenciaAlumno.asaFecha"));

        return criteria.list();     
    }

    /**
     * Method description
     *
     * @param curso
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<AsistenciaAlumnoNomina> find(Curso curso) {
        Criteria criteria = getSession().createCriteria(AsistenciaAlumnoNomina.class);

        criteria.createAlias("asistenciaAlumno", "asistenciaAlumno");
        criteria.add(eq("asistenciaAlumno.curso", curso));
        criteria.addOrder(asc("asistenciaAlumno.asaFecha"));

        return criteria.list();
    }
}
