/*
 * @(#)LogCertificacionPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.LogCertificacionRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.LogCertificacion;
import org.hibernate.Criteria;
import static org.hibernate.FetchMode.JOIN;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.sql.JoinType.LEFT_OUTER_JOIN;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class LogCertificacionPersistenceImpl extends CrudAbstractDAO<LogCertificacion, Long>
        implements LogCertificacionRepository {

    /**
     * Method description
     *
     * @param folio
     * @param verificador
     * @return
     */
    @Override
    public LogCertificacion find(Integer folio, String verificador) {
        Criteria criteria = getSession().createCriteria(LogCertificacion.class);

        criteria.setFetchMode("aluCar", JOIN);
        criteria.setFetchMode("aluCar.alumno", JOIN);
        criteria.setFetchMode("aluCar.plan", JOIN);
        criteria.setFetchMode("aluCar.plan.mencion", JOIN);
        criteria.setFetchMode("aluCar.plan.mencion.carrera", JOIN);
        criteria.setFetchMode("aluCar.plan.mencion.carrera.tcarrera", JOIN);
        criteria.setFetchMode("aluCar.plan.mencion.carrera.especialidad", JOIN);
        criteria.setFetchMode("tcertificado", JOIN);
        criteria.createCriteria("tramite", LEFT_OUTER_JOIN);
        criteria.add(eq("lcertFolio", folio));
        criteria.add(eq("lcertVerificador", verificador));

        return (LogCertificacion) criteria.uniqueResult();
    }
}
