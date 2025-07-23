/*
 * @(#)MencionInfoIntranetProfesorPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.MencionInfoIntranetProfesorRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;
import domain.model.MencionInfoIntranetProfesorView;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class MencionInfoIntranetProfesorPersistenceImpl
        extends CrudAbstractDAO<MencionInfoIntranetProfesorView, Long>
        implements MencionInfoIntranetProfesorRepository {

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<MencionInfoIntranetProfesorView> find(Integer rut) {
        Criteria criteria = getSession().createCriteria(MencionInfoIntranetProfesorView.class);

        criteria.add(eq("rut", rut));
        criteria.addOrder(asc("id.miniCodCar"));

        return criteria.list();
    }
}
