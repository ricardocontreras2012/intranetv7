/*
 * @(#)CrudAbstractDAO.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.persistence.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import static org.hibernate.criterion.Example.create;
import static org.hibernate.criterion.Order.asc;
import static infrastructure.util.LogUtil.logExceptionMessage;
import java.util.Arrays;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 * @param <T>
 * @param <ID>
 */
public abstract class CrudAbstractDAO<T, ID extends Serializable> implements CrudGenericDAO<T, ID> {

    private final Class<T> persistentClass;
    private Session session;

    /**
     *
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected CrudAbstractDAO() {
        Class clazz = getClass();

        while (!(clazz.getGenericSuperclass() instanceof ParameterizedType)) {
            clazz = clazz.getSuperclass();
        }

        persistentClass = (Class<T>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Method description
     *
     * @param s
     */
    @SuppressWarnings("unchecked")
    public void setSession(Session s) {
        this.session = s;
    }

    /**
     * Method description
     *
     * @return
     */
    protected Session getSession() {
        if (session == null) {
            throw new IllegalStateException("Session has not been set on DAO before usage");
        }

        return session;
    }

    /**
     * Method description
     *
     * @return
     */
    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    /**
     * Method description
     *
     * @param id
     * @param lock
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public T findById(ID id, boolean lock) {
        return (T) getSession().load(persistentClass, id);
    }

    /**
     * Method description
     *
     * @return
     * @throws HibernateException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() throws HibernateException {
        try {
            return findByCriteria();
        } catch (HibernateException he) {
            logExceptionMessage(he);

            throw he;
        }
    }

    /**
     * Method description
     *
     * @param orders
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAllOrdered(String[] orders) {
        try {
            Criteria crit = getSession().createCriteria(persistentClass);

            Arrays.stream(orders)
                    .forEach(order -> crit.addOrder(asc(order)));

            return crit.list();
        } catch (HibernateException he) {
            logExceptionMessage(he);
            throw he;
        }
    }

    /**
     * Method description
     *
     * @param exampleInstance
     * @param excludeProperty
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> findByExample(T exampleInstance, String[] excludeProperty) {
        Criteria crit = getSession().createCriteria(persistentClass);
        Example example = create(exampleInstance);

        Arrays.stream(excludeProperty)
                .forEach(example::excludeProperty);

        crit.add(example);

        return crit.list();
    }

    /**
     * Method description
     *
     * @param entity
     */
    @Override
    public void makePersistent(T entity) {
        try {
            getSession().saveOrUpdate(entity);
        } catch (HibernateException he) {
            logExceptionMessage(he);

            throw he;
        }
    }

    /**
     * Method description
     *
     * @param entity
     */
    @Override
    public void update(T entity) {
        try {
            getSession().saveOrUpdate(entity);
        } catch (HibernateException he) {
            logExceptionMessage(he);

            throw he;
        }
    }

    /**
     * Method description
     *
     * @param entity
     */
    @Override
    public void save(T entity) {
        try {
            getSession().save(entity);
        } catch (HibernateException he) {
            logExceptionMessage(he);

            throw he;
        }
    }

    /**
     * Method description
     *
     * @param entity
     */
    @Override
    public void makeTransient(T entity) {
        try {
            getSession().delete(entity);
        } catch (HibernateException he) {
            logExceptionMessage(he);

            throw he;
        }
    }

    /**
     * Method description
     */
    public void flush() {
        getSession().flush();
    }

    /**
     * Method description
     *
     * @throws HibernateException
     */
    public void clear() throws HibernateException {
        try {
            getSession().clear();
        } catch (HibernateException he) {
            logExceptionMessage(he);

            throw he;
        }
    }

    /**
     * Use this inside subclasses as a convenience method.
     *
     * @param criterion
     * @return
     * @throws HibernateException
     */
    @SuppressWarnings("unchecked")
    public List<T> findByCriteria(Criterion... criterion) throws HibernateException {
        try {
            Criteria crit = getSession().createCriteria(persistentClass);

            Arrays.stream(criterion)
                    .forEach(crit::add);

            return crit.list();
        } catch (HibernateException he) {
            logExceptionMessage(he);
            throw he;
        }
    }

}
