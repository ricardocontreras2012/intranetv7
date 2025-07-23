/*
 * @(#)CartolaViewRepository.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.AluCar;
import java.util.List;
import domain.model.CartolaView;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface CartolaViewRepository extends CrudGenericDAO<CartolaView, Long> {

    /**
     * Method description
     *
     * @param rut
     * @param carrera
     * @param agnoIng
     * @param semIng
     * @return
     */
    List<CartolaView> find(Integer rut, Integer carrera, Integer agnoIng, Integer semIng);
    
    /**
     * Method description
     *
     * @param rut
     * @param carrera
     * @param agnoIng
     * @param semIng
     * @param agno
     * @return
     */
    List<CartolaView> find(Integer rut, Integer carrera, Integer agnoIng, Integer semIng, Integer agno);

    /**
     * Method description
     *
     * @param aluCar
     * @return
     */
    List<CartolaView> find(AluCar aluCar);
    
        /**
     * Method description
     *
     * @param aluCar
     * @param agno
     * @return
     */
    List<CartolaView> find(AluCar aluCar, Integer agno);

    /**
     * Method description
     *
     * @param aluCar
     * @return
     */
    List<CartolaView> findAprobados(AluCar aluCar);
}
