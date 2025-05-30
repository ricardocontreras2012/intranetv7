/*
 * @(#)ActividadTeletrabajoPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.TareaActividadTeletrabajo;
import domain.model.TareaActividadTeletrabajoId;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Javier
 */
public interface TareaActividadTeletrabajoPersistence extends CrudGenericDAO<TareaActividadTeletrabajo, Long> {
    /**
     * Metodo que busca un tarea en particular a partir de su ID compuesto.
     *
     * @param rut
     * @param fecha
     * @param tarea
     * @return
     */
    TareaActividadTeletrabajo find(Integer rut, Date fecha, Integer tarea);
    
    
    /**
     * Metodo que busca todos los tareas que tiene una actividad en especifico 
     * a partir de las dos primeras variables del ID compuesto del mismo Tarea.
     *
     * @param rut
     * @param fecha
     * @return
     */
    List<TareaActividadTeletrabajo> findByActividad(Integer rut, Date fecha);    
    List<TareaActividadTeletrabajo> find(Integer rut, Date fecha);
    void insertTarea(Integer rut, Date fecha, String des); 
    void deleteTarea(TareaActividadTeletrabajoId id);
}
