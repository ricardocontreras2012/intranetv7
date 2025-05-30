/*
 * @(#)CartolaPersistenceViewImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.CartolaPersistenceView;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.AluCar;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Order.desc;
import static org.hibernate.criterion.Restrictions.eq;
import domain.model.CartolaView;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CartolaPersistenceViewImpl extends CrudAbstractDAO<CartolaView, Long>
        implements CartolaPersistenceView {

    /**
     * Method description
     *
     * @param rut
     * @param carrera
     * @param agnoIng
     * @param semIng
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CartolaView> find(Integer rut, Integer carrera, Integer agnoIng, Integer semIng) {
        Criteria criteria = getSession().createCriteria(CartolaView.class);

        criteria.add(eq("id.cartRut", rut));
        criteria.add(eq("id.cartCodCar", carrera));
        criteria.add(eq("id.cartAgnoIng", agnoIng));
        criteria.add(eq("id.cartSemIng", semIng));
        criteria.addOrder(asc("id.cartAgno"));
        criteria.addOrder(asc("id.cartSem"));
        criteria.addOrder(asc("id.cartAsign"));
        criteria.addOrder(asc("id.cartElect"));
        criteria.addOrder(desc("cartSitAlu"));

        return criteria.list();
    }
    
    /**
     * Method description
     *
     * @param rut
     * @param carrera
     * @param agnoIng
     * @param semIng
     * @param agno
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CartolaView> find(Integer rut, Integer carrera, Integer agnoIng, Integer semIng, Integer agno) {
        Criteria criteria = getSession().createCriteria(CartolaView.class);

        criteria.add(eq("id.cartRut", rut));
        criteria.add(eq("id.cartCodCar", carrera));
        criteria.add(eq("id.cartAgnoIng", agnoIng));
        criteria.add(eq("id.cartSemIng", semIng));
        criteria.add(eq("id.cartAgno", agno));
        criteria.addOrder(asc("id.cartAgno"));
        criteria.addOrder(asc("id.cartSem"));
        criteria.addOrder(asc("id.cartAsign"));
        criteria.addOrder(asc("id.cartElect"));
        criteria.addOrder(desc("cartSitAlu"));

        return criteria.list();
    }

    /**
     * Method description
     *
     * @param rut
     * @param carrera
     * @param agnoIng
     * @param semIng
     * @return
     */
    @SuppressWarnings("unchecked")
    private List<CartolaView> findAprobados(Integer rut, Integer carrera, Integer agnoIng, Integer semIng) {
        Criteria criteria = getSession().createCriteria(CartolaView.class);

        criteria.add(eq("id.cartRut", rut));
        criteria.add(eq("id.cartCodCar", carrera));
        criteria.add(eq("id.cartAgnoIng", agnoIng));
        criteria.add(eq("id.cartSemIng", semIng));
        criteria.add(eq("cartSitAlu", "A"));
        criteria.addOrder(asc("id.cartAgno"));
        criteria.addOrder(asc("id.cartSem"));
        criteria.addOrder(asc("id.cartAsign"));
        criteria.addOrder(asc("id.cartElect"));

        return criteria.list();
    }

    /**
     * Method description
     *
     * @param aluCar
     * @return
     */
    @Override
    public List<CartolaView> find(AluCar aluCar) {
        return find(aluCar.getId().getAcaRut(), aluCar.getId().getAcaCodCar(), aluCar.getId().getAcaAgnoIng(),
                aluCar.getId().getAcaSemIng());
    }
    
    /**
     * Method description
     *
     * @param aluCar
     * @return
     */
    @Override
    public List<CartolaView> find(AluCar aluCar, Integer agno) {
        return find(aluCar.getId().getAcaRut(), aluCar.getId().getAcaCodCar(), aluCar.getId().getAcaAgnoIng(),
                aluCar.getId().getAcaSemIng(), agno);
    }

    /**
     * Method description
     *
     * @param aluCar
     * @return
     */
    @Override
    public List<CartolaView> findAprobados(AluCar aluCar) {
        return findAprobados(aluCar.getId().getAcaRut(), aluCar.getId().getAcaCodCar(), aluCar.getId().getAcaAgnoIng(),
                aluCar.getId().getAcaSemIng());
    }
}
