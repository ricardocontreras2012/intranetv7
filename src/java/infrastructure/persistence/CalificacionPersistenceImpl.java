/*
 * @(#)CalificacionPersistenceImpl.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.persistence;

import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.AluCar;
import domain.model.AluCarId;
import domain.model.Calificacion;
import domain.model.CalificacionId;
import domain.model.CursoId;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.FetchMode.JOIN;
import org.hibernate.Query;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Order.desc;
import org.hibernate.criterion.Restrictions;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.sqlRestriction;
import static org.hibernate.sql.JoinType.LEFT_OUTER_JOIN;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import infrastructure.support.CalificacionCertificacionSupport;
import java.util.Collections;
import java.util.stream.Collectors;
import domain.repository.CalificacionRepository;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CalificacionPersistenceImpl extends CrudAbstractDAO<Calificacion, Long>
        implements CalificacionRepository {

    /**
     * Method description
     *
     * @param aluCar
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Calificacion> find(AluCar aluCar) {
        Criteria criteria = getSession().createCriteria(Calificacion.class);

        criteria.setFetchMode("procedenciaCalificacion", JOIN);
        criteria.setFetchMode("asignatura", JOIN);
        criteria.createCriteria("electivo", LEFT_OUTER_JOIN);
        criteria.add(eq("aluCar.id", aluCar.getId()));
        criteria.addOrder(asc("id.calAgno"));
        criteria.addOrder(asc("id.calSem"));
        criteria.addOrder(desc("calSitAlu"));
        criteria.addOrder(asc("id.calAsign"));
        criteria.addOrder(asc("id.calElect"));

        return criteria.list();
    }

    /**
     * Method description
     *
     * @param aluCar
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Calificacion> findCalProgramas(AluCar aluCar) {
        Criteria criteria = getSession().createCriteria(Calificacion.class);

        // Definimos la condición SQL con parámetros para evitar inyecciones SQL
        String sqlFilter = "EXISTS (SELECT 1 FROM malla WHERE "
                + " ma_cod_car = :acaCodCar "
                + " AND ma_cod_men = :acaCodMen "
                + " AND ma_cod_plan = :acaCodPlan "
                + " AND ma_asign = asi_cod) "
                + " AND (asi_elect != 'S' OR cal_proc NOT IN ('CON', 'CCN', 'EXT', 'EXE')) "
                + " AND (asi_elect != 'N' OR cal_proc NOT IN ('EXE'))";

        // Creamos alias para los atributos de las entidades relacionadas
        criteria.createAlias("asignatura", "asignatura");
        criteria.createCriteria("electivo", LEFT_OUTER_JOIN);  // Evaluar si este JOIN es necesario

        // Agregamos las restricciones sobre los campos de la entidad Calificacion
        criteria.add(eq("aluCar.id", aluCar.getId()));
        criteria.add(eq("calSitAlu", "A"));

        // Usamos sqlRestriction con parámetros para mayor seguridad
        criteria.add(sqlRestriction(sqlFilter,
                new Object[]{aluCar.getId().getAcaCodCar(), aluCar.getAcaCodMen(), aluCar.getAcaCodPlan()},
                new Type[]{StandardBasicTypes.INTEGER, StandardBasicTypes.INTEGER, StandardBasicTypes.INTEGER}));

        // Ordenamos el resultado según el nombre de la asignatura
        criteria.addOrder(asc("asignatura.asiNom"));

        return criteria.list();
    }

    /**
     * Method description
     *
     * @param rut
     * @param carrera
     * @param agnoIng
     * @param semIng
     * @param mencion
     * @param plan
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CalificacionCertificacionSupport> getI4NotasMalla(Integer rut, Integer carrera, Integer agnoIng,
            Integer semIng, Integer mencion, Integer plan) {
        try {
            Query query = getSession().getNamedQuery("I4CalificacionesMallaFunction");

            query.setParameter(0, rut, StandardBasicTypes.INTEGER);
            query.setParameter(1, carrera, StandardBasicTypes.INTEGER);
            query.setParameter(2, agnoIng, StandardBasicTypes.INTEGER);
            query.setParameter(3, semIng, StandardBasicTypes.INTEGER);
            query.setParameter(4, mencion, StandardBasicTypes.INTEGER);
            query.setParameter(5, plan, StandardBasicTypes.INTEGER);
            query.setParameter(6, "A", StandardBasicTypes.STRING);
            query.setParameter(7, "A", StandardBasicTypes.STRING);

            List<Object[]> results = query.list();

            // Usar Streams para transformar los resultados en objetos CalificacionCertificacionSupport
            return results.stream()
                    .map(obj -> {
                        CalificacionCertificacionSupport cal = new CalificacionCertificacionSupport();
                        CalificacionId id = new CalificacionId();

                        id.setCalRut(((Number) obj[0]).intValue());
                        id.setCalCodCar(((Number) obj[1]).intValue());
                        id.setCalAgnoIng(((Number) obj[2]).intValue());
                        id.setCalSemIng(((Number) obj[3]).intValue());
                        cal.setCalNombre((String) obj[4]);
                        cal.setCalNota((String) obj[5]);
                        id.setCalAgno(((Number) obj[6]).intValue());
                        id.setCalSem(((Number) obj[7]).intValue());
                        cal.setCalSitAlu((String) obj[8]);
                        cal.setCalProc((String) obj[9]);
                        id.setCalAsign(((Number) obj[10]).intValue());
                        id.setCalElect((String) obj[11]);
                        cal.setAsiSCT(((Number) obj[12]).intValue());
                        cal.setAsiTEL(((Number) obj[13]).intValue());

                        cal.setId(id);
                        return cal;
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList(); // Retorna una lista vacía en caso de error
        }
    }

    /**
     * Method description
     *
     * @param rut
     * @param carrera
     * @param agnoIng
     * @param semIng
     * @param mencion
     * @param plan
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CalificacionCertificacionSupport> getI4NotasOtras(Integer rut, Integer carrera, Integer agnoIng,
            Integer semIng, Integer mencion, Integer plan) {
        try {
            Query query = getSession().getNamedQuery("I4CalificacionesOtrasFunction");

            query.setParameter(0, rut, StandardBasicTypes.INTEGER);
            query.setParameter(1, carrera, StandardBasicTypes.INTEGER);
            query.setParameter(2, agnoIng, StandardBasicTypes.INTEGER);
            query.setParameter(3, semIng, StandardBasicTypes.INTEGER);
            query.setParameter(4, mencion, StandardBasicTypes.INTEGER);
            query.setParameter(5, plan, StandardBasicTypes.INTEGER);
            query.setParameter(6, "A", StandardBasicTypes.STRING);
            query.setParameter(7, "A", StandardBasicTypes.STRING);

            List<Object[]> results = query.list();

            // Usamos Streams para transformar los resultados en objetos CalificacionCertificacionSupport
            return results.stream()
                    .map(obj -> {
                        CalificacionCertificacionSupport cal = new CalificacionCertificacionSupport();
                        CalificacionId id = new CalificacionId();

                        id.setCalRut(((Number) obj[0]).intValue());
                        id.setCalCodCar(((Number) obj[1]).intValue());
                        id.setCalAgnoIng(((Number) obj[2]).intValue());
                        id.setCalSemIng(((Number) obj[3]).intValue());
                        cal.setCalNombre((String) obj[4]);
                        cal.setCalNota((String) obj[5]);
                        id.setCalAgno(((Number) obj[6]).intValue());
                        id.setCalSem(((Number) obj[7]).intValue());
                        cal.setCalSitAlu((String) obj[8]);
                        cal.setCalProc((String) obj[9]);
                        id.setCalAsign(((Number) obj[10]).intValue());
                        id.setCalElect((String) obj[11]);
                        cal.setId(id);
                        return cal;
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList(); // Retorna una lista vacía en caso de error
        }
    }

    /**
     * Method description
     *
     * @param rut
     * @param carrera
     * @param agnoIng
     * @param semIng
     * @param mencion
     * @param plan
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CalificacionCertificacionSupport> getI4NotasAdicionales(Integer rut, Integer carrera, Integer agnoIng,
            Integer semIng, Integer mencion, Integer plan) {
        try {
            Query query = getSession().getNamedQuery("I4CalificacionesAdicionalesFunction");

            query.setParameter(0, rut, StandardBasicTypes.INTEGER);
            query.setParameter(1, carrera, StandardBasicTypes.INTEGER);
            query.setParameter(2, agnoIng, StandardBasicTypes.INTEGER);
            query.setParameter(3, semIng, StandardBasicTypes.INTEGER);
            query.setParameter(4, mencion, StandardBasicTypes.INTEGER);
            query.setParameter(5, plan, StandardBasicTypes.INTEGER);

            List<Object[]> results = query.list();

            // Usamos Streams para transformar los resultados en objetos CalificacionCertificacionSupport
            return results.stream()
                    .map(obj -> {
                        CalificacionCertificacionSupport cal = new CalificacionCertificacionSupport();
                        CalificacionId id = new CalificacionId();

                        id.setCalRut(((Number) obj[0]).intValue());
                        id.setCalCodCar(((Number) obj[1]).intValue());
                        id.setCalAgnoIng(((Number) obj[2]).intValue());
                        id.setCalSemIng(((Number) obj[3]).intValue());
                        cal.setCalNombre((String) obj[4]);
                        cal.setCalNota((String) obj[5]);
                        id.setCalAgno(((Number) obj[6]).intValue());
                        id.setCalSem(((Number) obj[7]).intValue());
                        cal.setCalSitAlu((String) obj[8]);
                        cal.setCalProc((String) obj[9]);

                        cal.setId(id);
                        return cal;
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList(); // Retorna una lista vacía en caso de error
        }
    }

    /**
     * Method description
     *
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Calificacion> find(CursoId id) {
        Criteria criteria = getSession().createCriteria(Calificacion.class);

        criteria.createAlias("aluCar", "aluCar");
        criteria.createAlias("aluCar.alumno", "alumno");
        criteria.createAlias("procedenciaCalificacion", "procedenciaCalificacion");
        criteria.add(eq("id.calAsign", id.getCurAsign()));
        criteria.add(eq("id.calElect", id.getCurElect()));
        criteria.add(eq("calCoord", id.getCurCoord()));
        criteria.add(eq("calSecc", id.getCurSecc()));
        criteria.add(eq("id.calAgno", id.getCurAgno()));
        criteria.add(eq("id.calSem", id.getCurSem()));
        criteria.add(Restrictions.in("procedenciaCalificacion.procCod", new String[]{"ANO", "ACO", "ARE"}));
        criteria.addOrder(asc("alumno.aluPaterno"));
        criteria.addOrder(asc("alumno.aluMaterno"));
        criteria.addOrder(asc("alumno.aluNombre"));

        return criteria.list();
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Integer getUltimoAgnoCalificado(AluCarId id) {
        String sql = "select max(cal_agno) from calificacion where "
                + "cal_rut =:rut AND cal_cod_car=:carrera AND "
                + "cal_agno_ing = :agnoIng AND cal_sem_ing=:semIng";

        Query query = getSession().createSQLQuery(sql);
        query.setParameter("rut", id.getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter("carrera", id.getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter("agnoIng", id.getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter("semIng", id.getAcaSemIng(), StandardBasicTypes.INTEGER);

        return ((Number) query.uniqueResult()).intValue();

    }
}
