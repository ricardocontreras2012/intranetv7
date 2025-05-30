/*
 * @(#)ActaNominaPersistenceView.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.CursoId;
import java.util.List;
import domain.model.ActaNominaView;

/**
 *
 * @author Ricardo Contreras S.
 */
public interface ActaNominaPersistenceView extends CrudGenericDAO<ActaNominaView, Long> {

    /**
     * Method description
     *
     *
     * @param cursoId
     *
     * @return
     */
    List<ActaNominaView> find(CursoId cursoId);
    List<ActaNominaView> find(Integer folio, Integer agno, Integer sem);
}
