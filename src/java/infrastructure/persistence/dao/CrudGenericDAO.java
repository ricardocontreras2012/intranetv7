/*
 * @(#)CrudGenericDAO.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.persistence.dao;

import java.io.Serializable;
import java.util.List;
import infrastructure.util.InfrastructureExceptionUtil;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 * @param <T>
 * @param <ID>
 */
public interface CrudGenericDAO<T, ID extends Serializable> {

    /**
     * Method description
     *
     * @param id
     * @param lock
     * @return
     * @throws InfrastructureExceptionUtil
     */
    T findById(ID id, boolean lock) throws InfrastructureExceptionUtil;

    /**
     * Method description
     *
     * @return
     * @throws InfrastructureExceptionUtil
     */
    List<T> findAll() throws InfrastructureExceptionUtil;

    /**
     * Method description
     *
     * @param orders
     * @return
     * @throws InfrastructureExceptionUtil
     */
    List<T> findAllOrdered(String[] orders) throws InfrastructureExceptionUtil;

    /**
     * Method description
     *
     * @param exampleInstance
     * @param excludeProperty
     * @return
     * @throws InfrastructureExceptionUtil
     */
    List<T> findByExample(T exampleInstance, String[] excludeProperty) throws InfrastructureExceptionUtil;

    /**
     * Method description
     *
     * @param entity
     * @throws InfrastructureExceptionUtil
     */
    void makePersistent(T entity) throws InfrastructureExceptionUtil;

    /**
     * Method description
     *
     * @param entity
     * @throws InfrastructureExceptionUtil
     */
    void save(T entity) throws InfrastructureExceptionUtil;

    /**
     * Method description
     *
     * @param entity
     * @throws InfrastructureExceptionUtil
     */
    void update(T entity) throws InfrastructureExceptionUtil;

    /**
     * Method description
     *
     * @param entity
     * @throws InfrastructureExceptionUtil
     */
    void makeTransient(T entity) throws InfrastructureExceptionUtil;
}
