/*
 * @(#)ComentarioEncuestaDocentePersistenceImpl.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.persistence;

import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.ComentarioEncuestaAyudante;
import domain.model.Curso;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.criterion.Restrictions.eq;
import domain.repository.ComentarioEncuestaAyudanteRepository;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class ComentarioEncuestaAyudantePersistenceImpl extends CrudAbstractDAO<ComentarioEncuestaAyudante, Long>
        implements ComentarioEncuestaAyudanteRepository {

    /**
     * Method description
     *
     * @param curso
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ComentarioEncuestaAyudante> find(Curso curso) {
        getSession().clear();    // Borra la cache de session (sino repite valores anteriores)

        Criteria criteria = getSession().createCriteria(ComentarioEncuestaAyudante.class);

        criteria.createAlias("cursoAyudante", "cursoAyudante");
        criteria.add(eq("cursoAyudante.curso.id", curso.getId()));

        return criteria.list();
    }
}
