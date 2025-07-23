/*
 * @(#)TevaluacionPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.TevaluacionRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.CursoId;
import domain.model.Tevaluacion;
import org.hibernate.Query;
import org.hibernate.type.StandardBasicTypes;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class TevaluacionPersistenceImpl extends CrudAbstractDAO<Tevaluacion, Long>
        implements TevaluacionRepository {

    /**
     * Method description
     *
     * @param cursoId
     */
    @Override
    public void generaPlanilla(CursoId cursoId) {
        Query query = getSession().createSQLQuery("{ call genera_planilla(?,?,?,?,?,?) }");

        query.setParameter(0, cursoId.getCurAsign(), StandardBasicTypes.INTEGER);
        query.setParameter(1, cursoId.getCurElect(), StandardBasicTypes.STRING);
        query.setParameter(2, cursoId.getCurCoord(), StandardBasicTypes.STRING);
        query.setParameter(3, cursoId.getCurSecc(), StandardBasicTypes.INTEGER);
        query.setParameter(4, cursoId.getCurAgno(), StandardBasicTypes.INTEGER);
        query.setParameter(5, cursoId.getCurSem(), StandardBasicTypes.INTEGER);
        query.executeUpdate();
    }
}
