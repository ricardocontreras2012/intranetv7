/*
 * @(#)ActionPersistence.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.WebUserAction;
import java.io.Serializable;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface ActionPersistence extends CrudGenericDAO<Object, Serializable> {
    /**
     *
     * @param user
     * @param action
     * @return
     */
    WebUserAction find(String user, String action);
    void log(String action);
    String getActionLogin(String user);
    String setEmail(String user);
}
