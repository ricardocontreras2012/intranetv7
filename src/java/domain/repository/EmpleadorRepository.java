/*
 * @(#)EmpleadorPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.Empleador;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface EmpleadorRepository extends CrudGenericDAO<Empleador, Long> {

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    Empleador find(Integer rut);


    /**
     * Method description
     *
     * @param nombre
     * @return
     */
    List<Empleador> find(String nombre);
}
