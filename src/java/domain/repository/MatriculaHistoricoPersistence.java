/*
 * @(#)MatriculaHistoricoPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.AluCar;
import domain.model.AluCarId;
import domain.model.MatriculaHistorico;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface MatriculaHistoricoPersistence extends CrudGenericDAO<MatriculaHistorico, Long> {

    /**
     * Method description
     *
     * @param aluCar
     * @return
     */
    List<MatriculaHistorico> find(AluCar aluCar);

    /**
     * Method description
     *
     *
     * @param id
     * @param agno
     * @param sem
     *
     * @return
     */
    Integer find(AluCarId id, Integer agno, Integer sem);
    List<MatriculaHistorico> findMatCert(AluCar aluCar, String userType);
    List<MatriculaHistorico> find(Integer agno, Integer sem, Integer tCarrera, Integer especialidad, String jornada );
}
