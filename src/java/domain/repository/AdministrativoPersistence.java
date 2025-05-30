/*
 * @(#)AdministrativoPersistence.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.Administrativo;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface AdministrativoPersistence extends CrudGenericDAO<Administrativo, Long> {

    /**
     * Method description
     *
     * @param rut
     * @param password
     * @return
     */
    Administrativo find(Integer rut, String password);

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    Administrativo find(Integer rut);

    /**
     * Method description
     *
     * @param rut
     * @param password
     */
    void setPassword(Integer rut, String password);

    /**
     * Method description
     *
     * @param rut
     * @param email
     */
    void setEmail(Integer rut, String email);

    /**
     * Method description
     *
     * @param rut
     */
    void setLastLogin(Integer rut);

    /**
     * Method description
     *
     *
     * @param rut
     * @param trabajo
     *
     * @return
     */


    Administrativo trabaja(Integer rut, String trabajo);
    Administrativo getRegistrador(Integer unidad);
}
