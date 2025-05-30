/*
 * @(#)FuncionarioTeletrabajoPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.FuncionarioTeletrabajo;
import java.util.List;

/**
 *
 * @author Javier
 */
public interface FuncionarioTeletrabajoPersistence extends CrudGenericDAO<FuncionarioTeletrabajo, Long> {
    /**
     * Metodo que recibe el rut de un subordinado y 
     * retorna dicho subordinado
     *
     * @param rut
     * @return
     */
    FuncionarioTeletrabajo find(Integer rut);
    
    /**
     * Metodo que, a partir del rut del jefe, 
     * busca todos los sbordinados que tienen a dicho jefe
     *
     * @param rutJefe
     * @return
     */
    List<FuncionarioTeletrabajo> findSubordinados(Integer rutJefe);
}
