/*
 * @(#)ActaConvalidacionPersistenceImpl.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.ActaConvalidacionPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.ActaConvalidacion;
import domain.model.AluCarId;
import org.hibernate.Query;
import org.hibernate.type.StandardBasicTypes;

/**
 *
 * @author Ricardo Contreras S.
 */
public class ActaConvalidacionPersistenceImpl extends CrudAbstractDAO<ActaConvalidacion, Long>
        implements ActaConvalidacionPersistence {

    /**
     *
     * @param folio
     * @param agno
     * @param sem
     * @param acaId
     */
    @Override
    public void crearActa(Integer folio, Integer agno, Integer sem, AluCarId acaId) {
        String hql = "insert into Acta_Convalidacion (aco_folio, aco_agno, aco_sem, aco_rut, aco_cod_car, aco_agno_ing, aco_sem_ing, aco_estado) VALUES"
                + "(:folio, :agno, :sem, :rut, :carrera, :agno_ing, :sem_ing, :estado)";

            Query query = getSession().createSQLQuery(hql);

            query.setParameter("folio", folio, StandardBasicTypes.INTEGER);
            query.setParameter("agno", agno, StandardBasicTypes.INTEGER);
            query.setParameter("sem", sem, StandardBasicTypes.INTEGER);
            query.setParameter("rut", acaId.getAcaRut(), StandardBasicTypes.INTEGER);
            query.setParameter("carrera", acaId.getAcaCodCar(), StandardBasicTypes.INTEGER);
            query.setParameter("agno_ing", acaId.getAcaAgnoIng(), StandardBasicTypes.INTEGER);
            query.setParameter("sem_ing", acaId.getAcaSemIng(), StandardBasicTypes.INTEGER);
            query.setParameter("estado", "G", StandardBasicTypes.STRING);

            query.executeUpdate();
    }
}
