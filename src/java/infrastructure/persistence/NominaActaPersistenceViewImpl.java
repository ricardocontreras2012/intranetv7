/*
 * @(#)NominaActaPersistenceViewImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.NominaActaPersistenceView;
import infrastructure.persistence.dao.CrudAbstractDAO;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.criterion.Restrictions.eq;
import domain.model.NominaActaView;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class NominaActaPersistenceViewImpl extends CrudAbstractDAO<NominaActaView, Long>
        implements NominaActaPersistenceView {

    /**
     * Method description
     *
     * @param agno
     * @param sem
     * @param correl
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<NominaActaView> find(Integer agno, Integer sem, Integer correl) {
        Criteria criteria = getSession().createCriteria(NominaActaView.class);

        criteria.add(eq("nomCorrel", correl));
        criteria.add(eq("acalAgno", agno));
        criteria.add(eq("acalSem", sem));

        return criteria.list();
    }
}
