/*
 * @(#)RequisitoGradoTituloAdicPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.AluCar;
import domain.model.RequisitoLogroAdicional;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface RequisitoGradoTituloAdicRepository extends CrudGenericDAO<RequisitoLogroAdicional, Long> {

    /**
     * Method description
     *
     * @param aluCar
     * @return
     */
    List<RequisitoLogroAdicional> find(AluCar aluCar);

    /**
     * Method description
     *
     * @param tipoCarrera
     * @param especialidad
     * @param jornada
     * @return
     */
    List<RequisitoLogroAdicional> find(Integer tipoCarrera, Integer especialidad, String jornada);
}
