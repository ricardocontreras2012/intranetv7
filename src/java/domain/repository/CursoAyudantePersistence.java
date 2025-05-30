/*
 * @(#)CursoAyudantePersistence.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.AluCarId;
import domain.model.CursoAyudante;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface CursoAyudantePersistence extends CrudGenericDAO<CursoAyudante, Long> {


    /**
     * Method description
     *
     *
     * @param aluCarId
     * @param agno
     * @param sem
     *
     * @return
     */
    List<CursoAyudante> getCursosEncuesta(AluCarId aluCarId, Integer agno, Integer sem);

    // List<CursoAyudante> getCursosEncuesta(Ayudante profesor);   
}
