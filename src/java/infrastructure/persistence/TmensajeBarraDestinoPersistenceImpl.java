/*
 * @(#)TmensajeBarraDestinoPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.TmensajeBarraDestinoPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.TmensajeBarraDestino;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.FetchMode.JOIN;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class TmensajeBarraDestinoPersistenceImpl extends CrudAbstractDAO<TmensajeBarraDestino, Long>
        implements TmensajeBarraDestinoPersistence {

    /**
     * Method description
     *
     * @param destino
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TmensajeBarraDestino> find(String destino) {
        Criteria criteria = getSession().createCriteria(TmensajeBarraDestino.class);

        criteria.setFetchMode("tmensajeDestinoSegmento", JOIN);
        criteria.add(eq("id.tbdDestino", destino));
        criteria.createAlias("tmensajeDestinoSegmento", "tmensajeDestinoSegmento");
        criteria.addOrder(asc("id.tbdCorrel"));

        return criteria.list();
    }
}
