/*
 * @(#)ActividadTeletrabajoPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.EvidenciaTareaActTeletrabajo;
import domain.model.EvidenciaTareaActTeletrabajoId;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Javier
 */
public interface EvidenciaTareaActTeletrabajoPersistence extends CrudGenericDAO<EvidenciaTareaActTeletrabajo, Long> {
    /**
     * Metodo que encuentra una evidencia en especifico a partir del ID
     * compuesto de l evidencia.
     *
     * @param rut
     * @param fecha
     * @param tarea
     * @param correl
     * @return
     */
    EvidenciaTareaActTeletrabajo find(Integer rut, Date fecha, Integer tarea, Integer correl);
    
    /**
     * Metodo que encuentra todas las evidencias de un tarea en particular, los
     * cuales son encontrados a partir del las primeras 3 variables del ID 
     * compuesto de la evidencia.
     *
     * @param rut
     * @param fecha
     * @param tarea
     * @return
     */
    List<EvidenciaTareaActTeletrabajo> findByTarea(Integer rut, Date fecha, Integer tarea);
    
    /**
     * Metodo
     *
     * @param rut
     * @param fecha
     * @param tarea
     * @param des
     * @param file
     */
    void insertEvidencia(Integer rut, Date fecha, Integer tarea, String des, String file);
    
    /**
     * Metodo
     *
     * @param id
     */
    void deleteEvidencia(EvidenciaTareaActTeletrabajoId id);
}
