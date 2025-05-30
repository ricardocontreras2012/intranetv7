/*
 * @(#)CcalidadPersistence.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.AluCar;
import domain.model.Ccalidad;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface CcalidadPersistence extends CrudGenericDAO<Ccalidad, Long> {

    /**
     * Method description
     *
     * @param aluCar
     * @return
     */
    List<Ccalidad> find(AluCar aluCar);

    /**
     * Method description
     *
     * @param agno
     * @param calidad
     * @return
     */
    List<Ccalidad> findxCalidad(Integer agno, Integer calidad);

    /**
     * Method description
     *
     *
     * @param aluCar
     *
     * @return
     */
    Ccalidad findxCalidad(AluCar aluCar);
    Ccalidad findxCalidad(AluCar aluCar, Integer calidad);
}
