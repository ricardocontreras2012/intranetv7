/*
 * @(#)CursoProfesorPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.CursoId;
import domain.model.DocenteHorario;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.FetchMode.JOIN;
import org.hibernate.Query;
import static org.hibernate.criterion.Restrictions.eq;
import org.hibernate.type.StandardBasicTypes;
import domain.repository.DocenteHorarioRepository;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class DocenteHorarioPersistenceImpl extends CrudAbstractDAO<DocenteHorario, Long>
        implements DocenteHorarioRepository {

    @SuppressWarnings("unchecked")
    @Override
    public List<DocenteHorario> findDocente(CursoId cursoId, String tipo) {
        Criteria criteria = getSession().createCriteria(DocenteHorario.class);
        if ("T".equals(tipo) || "L".equals(tipo)) {
            criteria.setFetchMode("profesor", JOIN);
        } else {
            criteria.setFetchMode("ayudante", JOIN);
        }
        criteria.setFetchMode("curso", JOIN);
        criteria.add(eq("curso.id", cursoId));
        criteria.add(eq("dhorTipo", tipo));

        return criteria.list();
    }

    @Override
    public void addDocente(CursoId id, Integer rut, String horario, String tipo) {

        Query query = getSession().getNamedQuery("AddDocenteFunction");

        query.setParameter(0, id.getCurAsign(), StandardBasicTypes.INTEGER);
        query.setParameter(1, id.getCurElect(), StandardBasicTypes.STRING);
        query.setParameter(2, id.getCurCoord(), StandardBasicTypes.STRING);
        query.setParameter(3, id.getCurSecc(), StandardBasicTypes.INTEGER);
        query.setParameter(4, id.getCurAgno(), StandardBasicTypes.INTEGER);
        query.setParameter(5, id.getCurSem(), StandardBasicTypes.INTEGER);
        query.setParameter(6, id.getCurComp(), StandardBasicTypes.STRING);
        query.setParameter(7, rut, StandardBasicTypes.INTEGER);
        query.setParameter(8, tipo, StandardBasicTypes.STRING);
        query.setParameter(9, "".equals(horario) ? "NULL" : horario.substring(0, 1), StandardBasicTypes.STRING);        
        query.setParameter(10, "".equals(horario) ? -1 : Integer.parseInt(horario.substring(1, 2)), StandardBasicTypes.INTEGER);

        query.executeUpdate();
    }

    @Override
    public void removeDocente(CursoId id, Integer rut, String horario, String tipo) {
        Query query = getSession().getNamedQuery("RemoveDocenteFunction");

        query.setParameter(0, id.getCurAsign(), StandardBasicTypes.INTEGER);
        query.setParameter(1, id.getCurElect(), StandardBasicTypes.STRING);
        query.setParameter(2, id.getCurCoord(), StandardBasicTypes.STRING);
        query.setParameter(3, id.getCurSecc(), StandardBasicTypes.INTEGER);
        query.setParameter(4, id.getCurAgno(), StandardBasicTypes.INTEGER);
        query.setParameter(5, id.getCurSem(), StandardBasicTypes.INTEGER);
        query.setParameter(6, id.getCurComp(), StandardBasicTypes.STRING);
        query.setParameter(7, rut, StandardBasicTypes.INTEGER);
        query.setParameter(8, tipo, StandardBasicTypes.STRING);
        query.setParameter(9, "".equals(horario) ? "NULL" : horario.substring(0, 1), StandardBasicTypes.STRING);
        query.setParameter(10,"".equals(horario) ? -1 : Integer.parseInt(horario.substring(1, 2)), StandardBasicTypes.INTEGER);

        query.executeUpdate();
    }
}
