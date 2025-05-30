/*
 * @(#)ActaConvalidacionAsignaturaPersistence.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.ActaConvalidacionAsignatura;
import java.math.BigDecimal;


/**
 *
 * @author Ricardo Contreras S.
 */
public interface ActaConvalidacionAsignaturaPersistence extends CrudGenericDAO<ActaConvalidacionAsignatura, Long> {

    /**
     *
     * @param folio
     * @param asign
     * @param electivo
     * @param cursada
     * @param nota
     */
    void convalidar(Integer folio, Integer asign, String electivo, String cursada, BigDecimal nota);
}
