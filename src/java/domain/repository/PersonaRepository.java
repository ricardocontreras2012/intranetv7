/*
 * @(#)PersonaPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.Persona;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface PersonaRepository extends CrudGenericDAO<Persona, Long> {

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    Persona find(Integer rut);
}
