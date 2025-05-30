/*
 * @(#)LogActaPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.LogActaPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.LogActa;

/**
 *
 * @author Ricardo Contreras S.
 */
public class LogActaPersistenceImpl extends CrudAbstractDAO<LogActa, Long>
        implements LogActaPersistence {
}
