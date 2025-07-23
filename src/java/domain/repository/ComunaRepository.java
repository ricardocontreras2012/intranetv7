/*
 * @(#)ComunaRepository.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.Comuna;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface ComunaRepository extends CrudGenericDAO<Comuna, Long> {

    /**
     * Method description
     *
     * @return
     */
    List<Comuna> find();
    Comuna find(Integer comuna);
}
