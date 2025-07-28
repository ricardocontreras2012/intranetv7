/*
 * @(#)TsolicitudPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.TsolicitudRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.AluCar;
import domain.model.Tsolicitud;
import java.util.List;
import org.hibernate.SQLQuery;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class TsolicitudPersistenceImpl extends CrudAbstractDAO<Tsolicitud, Long>
        implements TsolicitudRepository {

    @SuppressWarnings("unchecked")
    @Override
    public List<Tsolicitud> find(AluCar aluCar) {
        String strQuery
                = "SELECT tsol_codigo, "
                + "decode(tsol_codigo, 80, tsol_descrip || ': ' || expediente_pkg.get_logros_solicitud(?, ?, ?, ?, ?, ?), tsol_descrip) AS tsol_descrip, "
                + "tsol_tipo "
                + "FROM tsolicitud "
                + "WHERE get_puede_solicitar(?, ?, ?, ?, ?, ?, ?, ?, tsol_codigo, tsol_tipo, tsol_descrip) = 1 "
                + "ORDER BY tsol_tipo, tsol_codigo";

        SQLQuery query = getSession().createSQLQuery(strQuery)
                .addEntity("Tsolicitud", Tsolicitud.class);

        // Asignación de parámetros en el orden correcto (14 parámetros)
        query.setParameter(0, aluCar.getId().getAcaRut());      // get_logros_solicitud(1)
        query.setParameter(1, aluCar.getId().getAcaCodCar());   // 2
        query.setParameter(2, aluCar.getId().getAcaAgnoIng());  // 3
        query.setParameter(3, aluCar.getId().getAcaSemIng());   // 4
        query.setParameter(4, aluCar.getAcaCodMen());           // 5
        query.setParameter(5, aluCar.getAcaCodPlan());          // 6

        query.setParameter(6, aluCar.getId().getAcaRut());      // get_puede_solicitar(1)
        query.setParameter(7, aluCar.getId().getAcaCodCar());   // 2
        query.setParameter(8, aluCar.getId().getAcaAgnoIng());  // 3
        query.setParameter(9, aluCar.getId().getAcaSemIng());   // 4
        query.setParameter(10, aluCar.getAcaCodMen());          // 5
        query.setParameter(11, aluCar.getAcaCodPlan());         // 6
        query.setParameter(12, aluCar.getParametros().getAgnoAct()); // 7
        query.setParameter(13, aluCar.getParametros().getSemAct());  // 8

        return query.list();
    }

}
