/*
 * @(#)NominaActaViewRepository.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import java.util.List;
import domain.model.NominaActaView;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface NominaActaViewRepository extends CrudGenericDAO<NominaActaView, Long> {

    /**
     * Method description
     *
     * @param agno
     * @param sem
     * @param correl
     * @return
     */
    List<NominaActaView> find(Integer agno, Integer sem, Integer correl);
}
