/*
 * @(#)ComentarioEncuestaDocentePersistenceImpl.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.persistence;

import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.ComentarioEncuestaDocente;
import domain.model.Curso;
import domain.model.CursoProfesor;
import domain.model.CursoProfesorId;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.sqlRestriction;
import org.hibernate.type.StandardBasicTypes;
import domain.repository.ComentarioEncuestaDocenteRepository;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class ComentarioEncuestaDocentePersistenceImpl extends CrudAbstractDAO<ComentarioEncuestaDocente, Long>
        implements ComentarioEncuestaDocenteRepository {

    /**
     * Method description
     *
     * @param curso
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ComentarioEncuestaDocente> find(Curso curso, Integer rutProf, String tipo) {

        getSession().clear();    // Borra la cache de session (sino repite valores anteriores)       

        Criteria criteria = getSession().createCriteria(ComentarioEncuestaDocente.class);

        criteria.createAlias("cursoProfesor", "cursoProfesor");
        criteria.add(eq("cursoProfesor.profesor.profRut", rutProf));
        criteria.add(eq("cursoProfesor.curso.id", curso.getId()));
        criteria.add(sqlRestriction("exists (select * from encuesta_docente where edo_nro=ced_enc and edo_tipo='"+tipo+"')"));        

        return criteria.list();
    }

    @Override
    public void doUpdate(Integer rutAlu, CursoProfesor cursoProfesor, Integer encuesta, Integer correl, String comen1, String comen2) {
        String hql = "update comentario_encuesta_docente set "
                + "ced_comen1 = :comen1,"
                + "ced_comen2 = :comen2,"
                + "ced_correl = :correl,"
                + "ced_rut = NULL "
                + "where "
                + "ced_asign = :asign AND "
                + "ced_elect = :elect AND "
                + "ced_coord = :coord  AND "
                + "ced_secc = :secc AND "
                + "ced_agno = :agno AND "
                + "ced_sem = :sem AND "
                + "ced_enc = :encuesta AND "
                + "ced_rut_prof = :rut AND "
                + "ced_rut = :rutAlu";

        Query query = getSession().createSQLQuery(hql);
        
        CursoProfesorId id = cursoProfesor.getId();

        query.setParameter("asign", id.getCproAsign(), StandardBasicTypes.INTEGER);
        query.setParameter("elect", id.getCproElect(), StandardBasicTypes.STRING);
        query.setParameter("coord", id.getCproCoord(), StandardBasicTypes.STRING);
        query.setParameter("secc", id.getCproSecc(), StandardBasicTypes.INTEGER);
        query.setParameter("agno", id.getCproAgno(), StandardBasicTypes.INTEGER);
        query.setParameter("sem", id.getCproSem(), StandardBasicTypes.INTEGER);
        query.setParameter("rut", id.getCproRut(), StandardBasicTypes.INTEGER);
        query.setParameter("correl", correl, StandardBasicTypes.INTEGER);
        query.setParameter("rutAlu", rutAlu, StandardBasicTypes.INTEGER);
        query.setParameter("encuesta", encuesta, StandardBasicTypes.INTEGER);
        query.setParameter("comen1", comen1, StandardBasicTypes.STRING);
        query.setParameter("comen2", comen2, StandardBasicTypes.STRING);        
        
        query.executeUpdate();
    }
}
