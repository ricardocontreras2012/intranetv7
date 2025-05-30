/*
 * @(#)SolicitudPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.AluCar;
import domain.model.Solicitud;
import java.util.Date;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface SolicitudPersistence extends CrudGenericDAO<Solicitud, Long> {

    /**
     * Method description
     *
     * @param aluCar
     * @return
     */
    List<Solicitud> find(AluCar aluCar);

    /**
     * Method description
     *
     * @param rut
     * @param autoridad
     * @param estado
     * @return
     */
    List<Solicitud> findPracticasxEstado(Integer rut, Integer autoridad, Integer estado);

    /**
     *
     * @param folio
     * @param resolucion
     * @param respuesta
     * @param estado
     */
    void saveResolucion(Integer folio, String resolucion, String respuesta, Integer estado);
    
    /**
     * Method description
     *
     * @param rut
     * @param user
     * @param estado
     * @param fechaInicio
     * @param fechaFinal
     * @return
     */
    List<Solicitud> findSolicitudBetweenDates(Integer rut, String user, Integer estado, Date fechaInicio, Date fechaFinal);
    
    void modify(Integer folio, String solicita);
    Solicitud find(Integer folio);
}
