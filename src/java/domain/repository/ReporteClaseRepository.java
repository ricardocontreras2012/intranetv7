/*
 * @(#)ReporteClasePersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.CursoId;
import domain.model.ReporteClase;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface ReporteClaseRepository extends CrudGenericDAO<ReporteClase, Long> {

    /**
     * Method description
     *
     * @param cursoId
     * @return
     */
    List<ReporteClase> find(CursoId cursoId);

    /**
     * Method description
     *
     * @param reporte
     * @return
     */
    boolean exists(ReporteClase reporte);
}
