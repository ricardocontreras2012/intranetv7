/*
 * @(#)CarreraPersistence.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.Carrera;

/**
 * Interface description
 *
 * @author Ricardo Contreras S and Javier Frez V.
 *
 */
public interface CarreraPersistence extends CrudGenericDAO<Carrera, Long> {
    
    /**
     * Method description
     *
     * @param cod
     * @return
     */
    Carrera find(Integer cod);
}
