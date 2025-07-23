/*
 * @(#)EmpleadorPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.EmpleadorRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.Empleador;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import static org.hibernate.criterion.Restrictions.eq;
import infrastructure.util.ContextUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class EmpleadorPersistenceImpl extends CrudAbstractDAO<Empleador, Long> implements EmpleadorRepository {

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    @Override
    public Empleador find(Integer rut) {
        Criteria criteria = getSession().createCriteria(Empleador.class);

        criteria.add(eq("empRut", rut));

        return (Empleador) criteria.uniqueResult();
    }

    /**
     * Method description
     *
     * @param nombre
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Empleador> find(String nombre) {
        String strQuery = "SELECT * FROM empleador WHERE "
                + "(normaliza_string(upper(emp_nombre)) LIKE " + "'%'||normaliza_string('"
                + nombre.toUpperCase(ContextUtil.getLocale())
                + "')||'%') ORDER BY emp_nombre";
        Query query = getSession().createSQLQuery(strQuery).addEntity("empleador", Empleador.class);

        return query.list();
    }

}
