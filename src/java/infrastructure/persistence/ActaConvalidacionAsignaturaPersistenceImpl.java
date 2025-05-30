/*
 * @(#)ActaConvalidacionAsignaturaPersistenceImpl.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.ActaConvalidacionAsignaturaPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.ActaConvalidacionAsignatura;
import java.math.BigDecimal;
import org.hibernate.Query;
import org.hibernate.type.StandardBasicTypes;

/**
 *
 * @author Ricardo Contreras S.
 */
public class ActaConvalidacionAsignaturaPersistenceImpl extends CrudAbstractDAO<ActaConvalidacionAsignatura, Long>
        implements ActaConvalidacionAsignaturaPersistence {

    /**
     *
     * @param folio
     * @param asign
     * @param cursada
     * @param nota
     */
    @Override
    public void convalidar(Integer folio, Integer asign, String electivo, String cursada, BigDecimal nota) {
        String hql = "insert into Acta_Convalidacion_Asignatura (acoa_folio, acoa_asign, acoa_electivo, acoa_cursada, acoa_nota) VALUES"
                + "(:folio, :asign, :electivo, :cursada, :nota)";

            Query query = getSession().createSQLQuery(hql);

            query.setParameter("folio", folio, StandardBasicTypes.INTEGER);
            query.setParameter("asign", asign, StandardBasicTypes.INTEGER);
            query.setParameter("electivo", electivo, StandardBasicTypes.STRING);
            query.setParameter("cursada", cursada, StandardBasicTypes.STRING);
            query.setParameter("nota", nota, StandardBasicTypes.BIG_DECIMAL);

            query.executeUpdate();
    }
}
