/*
 * @(#)FuncionarioTeletrabajoPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.FuncionarioTeletrabajoPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.FuncionarioTeletrabajo;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;

/**
 *
 * @author Javier
 */
public class FuncionarioTeletrabajoPersistenceImpl extends CrudAbstractDAO<FuncionarioTeletrabajo, Long> implements FuncionarioTeletrabajoPersistence {
    /**
     * Metodo que recibe el rut de un subordinado y 
     * retorna dicho subordinado
     *
     * @param rut
     * @return
     */
    @Override
    public FuncionarioTeletrabajo find(Integer rut) {
        Criteria criteria = getSession().createCriteria(FuncionarioTeletrabajo.class);
        criteria.add(eq("ftelRut", rut));

        return (FuncionarioTeletrabajo) criteria.uniqueResult();
    }
    
    /**
     * Metodo que, a partir del rut del jefe, 
     * busca todos los sbordinados que tienen a dicho jefe
     *
     * @param rutJefe
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<FuncionarioTeletrabajo> findSubordinados(Integer rutJefe) {
        Criteria criteria = getSession().createCriteria(FuncionarioTeletrabajo.class);
        criteria.createAlias("funcionario", "funcionario");
        criteria.add(eq("ftelRutJefe", rutJefe));
        criteria.addOrder(asc("funcionario.funPaterno"));
        criteria.addOrder(asc("funcionario.funMaterno"));
        criteria.addOrder(asc("funcionario.funNombre"));
        
        return criteria.list();
    }
}
