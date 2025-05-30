/*
 * @(#)ActaConvalidacionPersistence.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.ActaConvalidacion;
import domain.model.AluCarId;

/**
 *
 * @author Ricardo Contreras S.
 */
public interface ActaConvalidacionPersistence extends CrudGenericDAO<ActaConvalidacion, Long> {

    /**
     *
     * @param folio
     * @param agno
     * @param sem
     * @param aluCarId
     */
    void crearActa(Integer folio, Integer agno, Integer sem, AluCarId aluCarId);
}
