/*
 * @(#)TmensajeDestinoPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.TmensajeDestinoPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.TmensajeDestino;
import domain.model.TmensajeOrigenDestino;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.hibernate.Criteria;
import static org.hibernate.FetchMode.JOIN;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.gt;
import static org.hibernate.criterion.Restrictions.le;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class TmensajeDestinoPersistenceImpl extends CrudAbstractDAO<TmensajeDestino, Long>
        implements TmensajeDestinoPersistence {

    /**
     * Method description
     *
     * @param user
     * @return
     */
    @Override
    public List<TmensajeDestino> find(String user) {
        return getTiposDestino(user, 1);
    }

    /**
     * Method description
     *
     * @param user
     * @return
     */
    @Override
    public List<TmensajeDestino> findSinCursos(String user) {
        return getTiposDestino(user, 2);
    }

    /**
     * Method description
     *
     * @param user
     * @return
     */
    @Override
    public List<TmensajeDestino> findOtros(String user) {
        return getTiposDestino(user, 3);
    }

    /**
     * Method description
     *
     * @param user
     * @param tipoBusqueda
     * @return
     */
    @SuppressWarnings("unchecked")
    private List<TmensajeDestino> getTiposDestino(String user, Integer tipoBusqueda) {
        Criteria criteria = getSession().createCriteria(TmensajeOrigenDestino.class);

        criteria.setFetchMode("tmensajeDestino", JOIN);
        criteria.add(eq("id.todOrigen", user));
        criteria.createAlias("tmensajeDestino", "tmensajeDestino");

        switch (tipoBusqueda) {
            case 1:
                criteria.add(le("tmensajeDestino.tmdCorrel", 150));

                break;

            case 2:
                criteria.add(eq("tmensajeDestino.tmdCorrel", 150));

                break;

            case 3:
                criteria.add(gt("tmensajeDestino.tmdCorrel", 150));

                break;

            default:
                break;
        }

        criteria.addOrder(asc("tmensajeDestino.tmdCorrel"));

        List<TmensajeOrigenDestino> origenDestinoList = criteria.list();
        List<TmensajeDestino> destinoList = new ArrayList<>();
        destinoList.addAll(origenDestinoList.stream()
                .map(TmensajeOrigenDestino::getTmensajeDestino)
                .collect(Collectors.toList()));

        return destinoList;
    }
}
