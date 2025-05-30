/*
 * @(#)LogInscripcionPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.AluCar;
import domain.model.LogInscripcion;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface LogInscripcionPersistence extends CrudGenericDAO<LogInscripcion, Long> {

    /**
     * Method description
     *
     * @param aluCar
     * @return
     */
    List<LogInscripcion> find(AluCar aluCar);
    
    /**
     * Method description
     *
     * @param aluCar
     * @param sem
     * @param agno
     * @return
     */
    List<LogInscripcion> findAgnoSem(AluCar aluCar, Integer sem, Integer agno);
}
