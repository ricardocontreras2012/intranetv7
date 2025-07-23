/*
 * @(#)ActaCalificacionNominaRepository.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.ActaCalificacionNomina;
import java.math.BigDecimal;
import domain.model.ActaNominaView;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface ActaCalificacionNominaRepository extends CrudGenericDAO<ActaCalificacionNomina, Long> {

    /**
     * Method description
     *
     *
     * @param actaNominaView
     */
    void putCalificacionNumerica(ActaNominaView actaNominaView);

    /**
     * Method description
     *
     *
     * @param actaNominaView
     */
    void putCalificacionConcepto(ActaNominaView actaNominaView);

    /**
     *
     * @param folio
     * @param agno
     * @param sem
     * @param rut
     * @param carrera
     * @param agnoIng
     * @param semIng
     * @param nota
     */
    void insertCalificacion(Integer folio, Integer agno, Integer sem, Integer rut, Integer carrera, Integer agnoIng, Integer semIng, BigDecimal nota);
}
