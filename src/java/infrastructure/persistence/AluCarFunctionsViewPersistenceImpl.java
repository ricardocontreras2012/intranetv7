/*
 * @(#)AluCarFunctionsViewPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.AluCar;
import org.hibernate.Criteria;
import static org.hibernate.criterion.Restrictions.eq;
import domain.model.AluCarFunctionsView;
import domain.repository.AluCarFunctionsViewRepository;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class AluCarFunctionsViewPersistenceImpl extends CrudAbstractDAO<AluCarFunctionsView, Long>
        implements AluCarFunctionsViewRepository {

    /**
     * Method description
     *
     * @param aluCar
     * @return
     */
    @Override
    public AluCarFunctionsView find(AluCar aluCar) {

        Criteria criteria = getSession().createCriteria(AluCarFunctionsView.class);

        criteria.add(eq("id", aluCar.getId()));

        return (AluCarFunctionsView) criteria.uniqueResult(); 
                
    }
}
