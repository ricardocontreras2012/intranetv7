/*
 * @(#)AdministrativoPersistenceImpl.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.persistence;

import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.Administrativo;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import static org.hibernate.criterion.Restrictions.idEq;
import static org.hibernate.criterion.Restrictions.sqlRestriction;
import org.hibernate.type.StringType;
import org.hibernate.type.StandardBasicTypes;
import domain.repository.AdministrativoRepository;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class AdministrativoPersistenceImpl extends CrudAbstractDAO<Administrativo, Long>
        implements AdministrativoRepository {

    /**
     * Method description
     *
     * @param rut
     * @param password
     * @return
     */
    @Override
    public Administrativo find(Integer rut, String password) {
        Criteria criteria = getSession().createCriteria(Administrativo.class);

        criteria.add(idEq(rut));
        criteria.add(sqlRestriction("valid_user_administrativo(adm_rut, (?))=1", password,
                new StringType()));

        return (Administrativo) criteria.uniqueResult();
    }

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    @Override
    public Administrativo find(Integer rut) {
        Criteria criteria = getSession().createCriteria(Administrativo.class);

        criteria.add(idEq(rut));

        return (Administrativo) criteria.uniqueResult();
    }

    /**
     * Method description
     *
     * @param rut
     * @param password
     */
    @Override
    public void setPassword(Integer rut, String password) {
        String hql = "update Administrativo set adm_password = get_hash(:rut, " + ":password) where adm_rut = :rut";
        Query query = getSession().createQuery(hql);

        query.setParameter("rut", rut.toString(), StandardBasicTypes.STRING);
        query.setParameter("password", password, StandardBasicTypes.STRING);
        query.executeUpdate();
    }

    /**
     * Method description
     *
     * @param rut
     * @param email
     */
    @Override
    public void setEmail(Integer rut, String email) {
        String hql = "update Administrativo set adm_email = :email where adm_rut = :rut";
        Query query = getSession().createQuery(hql);

        query.setParameter("rut", rut.toString(), StandardBasicTypes.STRING);
        query.setParameter("email", email, StandardBasicTypes.STRING);
        query.executeUpdate();
    }

    /**
     * Method description
     *
     * @param rut
     */
    @Override
    public void setLastLogin(Integer rut) {
        String hql = "update Administrativo set adm_last_login = SYSDATE where adm_rut = :rut";
        Query query = getSession().createQuery(hql);

        query.setParameter("rut", rut.toString(), StandardBasicTypes.STRING);
        query.executeUpdate();
    }

    @Override
    public Administrativo trabaja(Integer rut, String user) {        
        String sql = "select * from Administrativo where exists (SELECT *"
                + " FROM labor_realizada, labor"
                + " WHERE lrea_rut = adm_rut and"
                + " lrea_labor = lab_cod and"
                + " lab_user= ?) and"
                + " adm_rut = ?";

        SQLQuery query = getSession().createSQLQuery(sql).addEntity("Administrativo", Administrativo.class);

        // Establecemos los parámetros para la consulta
        query.setParameter(0, user, StandardBasicTypes.STRING);
        query.setParameter(1, rut, StandardBasicTypes.INTEGER);

        // Ejecutamos la consulta y obtenemos los resultados
        return (Administrativo)query.uniqueResult();
    }
    
    @Override
    public Administrativo getRegistrador(Integer unidad) {        
        String sql = "SELECT * from Administrativo where exists (SELECT *"
                + " FROM labor_realizada, labor"
                + " WHERE lrea_rut = adm_rut and"
                + " lrea_labor = lab_cod and"
                + " lrea_rut = adm_rut and"
                + " facultad_pkg.get_unidad_x_unidad(lrea_unidad)=? and"
                + " lrea_labor=100)";

        SQLQuery query = getSession().createSQLQuery(sql).addEntity("Administrativo", Administrativo.class);

        // Establecemos los parámetros para la consulta
        query.setParameter(0, unidad, StandardBasicTypes.INTEGER);

        // Ejecutamos la consulta y obtenemos los resultados
        return (Administrativo)query.uniqueResult();
    }
}
