/*
 * @(#)LogActaPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.LogActa;


/**
 *
 * @author Ricardo Contreras S.
 */
public interface LogActaPersistence extends CrudGenericDAO<LogActa, Long> {
}
