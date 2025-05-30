/*
 * @(#)TsolicitudPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.TsolicitudPersistence;
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
        implements TsolicitudPersistence {

    @SuppressWarnings("unchecked")
    @Override    
    public List<Tsolicitud> find(AluCar aluCar) {
        String strQuery
                = "SELECT tsol_codigo, "
                + "decode(tsol_codigo, 80, tsol_descrip || ': ' || expediente_pkg.get_logros_solicitud("
                + ":rut, :codCar, :agnoIng, :semIng, :codMen, :codPlan), tsol_descrip) AS tsol_descrip, "
                + "tsol_tipo "
                + "FROM tsolicitud "
                + "WHERE get_puede_solicitar("
                + ":rut, :codCar, :agnoIng, :semIng, :codMen, :codPlan, "
                + ":agnoAct, :semAct, tsol_codigo, tsol_tipo, tsol_descrip) = 1 "
                + "ORDER BY tsol_tipo, tsol_codigo";

        SQLQuery query = getSession().createSQLQuery(strQuery)
                .addEntity("Tsolicitud", Tsolicitud.class);

        query.setParameter("rut", aluCar.getId().getAcaRut());
        query.setParameter("codCar", aluCar.getId().getAcaCodCar());
        query.setParameter("agnoIng", aluCar.getId().getAcaAgnoIng());
        query.setParameter("semIng", aluCar.getId().getAcaSemIng());
        query.setParameter("codMen", aluCar.getAcaCodMen());
        query.setParameter("codPlan", aluCar.getAcaCodPlan());
        query.setParameter("agnoAct", aluCar.getParametros().getAgnoAct());
        query.setParameter("semAct", aluCar.getParametros().getSemAct());

        return query.list();
    }
}
