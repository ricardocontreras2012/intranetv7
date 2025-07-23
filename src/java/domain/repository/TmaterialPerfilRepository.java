/*
 * @(#)TmaterialPerfilPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.TmaterialPerfil;
import java.util.List;
import org.hibernate.HibernateException;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface TmaterialPerfilRepository extends CrudGenericDAO<TmaterialPerfil, Long> {

    /**
     * Method description
     *
     * @return
     * @throws HibernateException
     */
    List<TmaterialPerfil> find() throws HibernateException;
}
