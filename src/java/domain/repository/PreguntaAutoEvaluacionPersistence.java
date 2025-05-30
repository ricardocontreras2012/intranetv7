/*
 * @(#)PregEnctaPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.PreguntaAutoEvaluacion;
import java.util.List;


/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface PreguntaAutoEvaluacionPersistence extends CrudGenericDAO<PreguntaAutoEvaluacion, Long> {

    /**
     *
     * @param agno
     * @param sem
     * @return
     */
    List<PreguntaAutoEvaluacion> find(Integer agno, Integer sem);
}
