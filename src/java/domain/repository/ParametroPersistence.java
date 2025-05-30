/*
 * @(#)ParametroPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.Parametro;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface ParametroPersistence extends CrudGenericDAO<Parametro, Long> {

    /**
     * Method description
     *
     * @param param
     * @param unidad
     * @return
     */
    Parametro find(String param);
}
