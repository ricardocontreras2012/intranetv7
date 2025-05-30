/*
 * @(#)MatriculaHistoricoPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.MatriculaHistoricoPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.AluCar;
import domain.model.AluCarId;
import domain.model.MatriculaHistorico;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import static org.hibernate.criterion.Order.desc;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;
import org.hibernate.type.StandardBasicTypes;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class MatriculaHistoricoPersistenceImpl extends CrudAbstractDAO<MatriculaHistorico, Long>
        implements MatriculaHistoricoPersistence {

    /**
     * Method description
     *
     * @param aluCar
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<MatriculaHistorico> find(AluCar aluCar) {
        Criteria criteria = getSession().createCriteria(MatriculaHistorico.class);

        criteria.add(eq("aluCar.id", aluCar.getId()));
        criteria.addOrder(asc("id.mathAgno"));
        criteria.addOrder(asc("id.mathSem"));

        return criteria.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<MatriculaHistorico> find(Integer agno, Integer sem, Integer tCarrera, Integer especialidad, String jornada) {
    Criteria criteria = getSession().createCriteria(MatriculaHistorico.class);

    // Crear alias para las relaciones entre las entidades
    criteria.createAlias("aluCar", "aluCar");
    criteria.createAlias("aluCar.alumno", "alumno");
    criteria.createAlias("aluCar.plan", "plan");
    criteria.createAlias("plan.mencion", "mencion");
    criteria.createAlias("mencion.carrera", "carrera");
    criteria.createAlias("carrera.especialidad", "especialidad");
    criteria.createAlias("carrera.tcarrera", "tcarrera");


    // Agregar los filtros de manera segura 
    criteria.add(eq("id.mathAgno", agno));
    criteria.add(eq("id.mathSem", sem));

    // Filtrar por la relación entre tcarrera, especialidad y jornada
    criteria.add(eq("tcarrera.tcrCtip", tCarrera));  // Filtrar por tcarrera
    criteria.add(eq("especialidad.espCod", especialidad));  // Filtrar por especialidad
    criteria.add(eq("especialidad.espJornada", jornada));  // Filtrar por jornada

    // Ordenar por los apellidos del alumno
    criteria.addOrder(asc("alumno.aluPaterno"));
    criteria.addOrder(asc("alumno.aluMaterno"));

    return criteria.list();  
}

    /**
     * Method description
     *
     * @param agno
     * @param sem
     * @return
     */
    @Override
    public Integer find(AluCarId id, Integer agno, Integer sem) {
        Query query = getSession().getNamedQuery("EstaMatriculadoFunction");

        query.setParameter(0, id.getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter(1, id.getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(2, id.getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(3, id.getAcaSemIng(), StandardBasicTypes.INTEGER);
        query.setParameter(4, agno, StandardBasicTypes.INTEGER);
        query.setParameter(5, sem, StandardBasicTypes.INTEGER);

        return (Integer) query.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<MatriculaHistorico> findMatCert(AluCar aluCar, String userType) {
  
        Criteria criteria = getSession().createCriteria(MatriculaHistorico.class);

        criteria.add(eq("aluCar.id", aluCar.getId()));
        /*if (aluCar.getAluCarFunction().getFlagNuevo() == 0) {//Es antiguo
            String sqlFilter = "get_semestres(math_agno, math_sem, (select to_number(par_valor) from parametro where par_nom_var = 'agno_mat'), (select to_number(par_valor)  from parametro where par_nom_var = 'sem_mat')) <=" + ("AL".equals(userType) ? 20 : 50);
            criteria.add(sqlRestriction(sqlFilter));
        }*/
        criteria.addOrder(desc("id.mathAgno"));
        criteria.addOrder(desc("id.mathSem"));

        return criteria.list();        
    }
}
