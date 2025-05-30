/*
 * @(#)ActividadTeletrabajoPersistence.java
 *
 * Copyright (c) Copyright (c) 2025 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.ActividadTeletrabajo;
import domain.model.ActividadTeletrabajoId;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Javier
 */
public interface ActividadTeletrabajoPersistence extends CrudGenericDAO<ActividadTeletrabajo, Long> {
    /**
     * Method description
     *
     * @param rut
     * @param fecha
     * @return
     */
    ActividadTeletrabajo find(Integer rut, Date fecha);
    
    /**
     * Method description
     *
     * @param rut
     * @param fechaInicio
     * @param fechaFinal
     * @return
     */
    List<ActividadTeletrabajo> findActividadesBetweenDates(Integer rut, Date fechaInicio, Date fechaFinal);
    
    /**
     * Method description
     *
     * @return
     */
    @Override
    List<ActividadTeletrabajo> findAll();

    List<ActividadTeletrabajo> find(Integer rutJefe);
    List<ActividadTeletrabajo> find(Integer rutJefe, Integer rutFun);
    List<ActividadTeletrabajo> findByFun(Integer rutFun);
    void insertActividad(Integer rut, Date fecha, String des);
    void deleteActividad(ActividadTeletrabajoId id);
    void updateActividad(ActividadTeletrabajoId id, String estado);
}
