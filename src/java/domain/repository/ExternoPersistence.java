/*
 * @(#)ExternoPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.Externo;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface ExternoPersistence extends CrudGenericDAO<Externo, Long> {

    /**
     * Method description
     *
     * @return
     */
    List<Externo> find();

    /**
     * Method description
     *
     * @param rut
     * @param password
     * @return
     */
    Externo find(Integer rut, String password);

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    Externo find(Integer rut);

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
    void setMisDatos(Integer rut, String email);

    /**
     * Method description
     *
     * @param rut
     */
    void setLastLogin(Integer rut);
}
