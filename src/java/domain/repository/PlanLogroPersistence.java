/*
 * @(#)PlanLogroPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.AluCarId;
import domain.model.PlanId;
import domain.model.PlanLogro;
import java.util.List;

/**
 *
 * @author Ricardo Contreras S.
 */
public interface PlanLogroPersistence extends CrudGenericDAO<PlanLogro, Long> {

    /**
     * Method description
     *
     *
     * @param carrera
     * @param mencion
     * @param plan
     * @param correl
     *
     * @return
     */
    PlanLogro find(Integer carrera, Integer mencion, Integer plan, Integer correl);
    List<PlanLogro> find(AluCarId idA, PlanId idP, Integer logro);
}
