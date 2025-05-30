/*
 * @(#)ActaPersistenceView.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import java.util.List;
import domain.model.ActaDeptoView;
import domain.model.ActaFacultadView;
import domain.model.ActaMencionView;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface ActaPersistenceView extends CrudGenericDAO<ActaMencionView, Long> {

    /**
     * Method description
     *
     * @param agno
     * @param sem
     * @param tipoCarrera
     * @param especialidad
     * @param jornada
     * @param rut
     * @param userType
     * @return
     */
    List<ActaMencionView> findMencion(Integer agno, Integer sem, Integer tipoCarrera, Integer especialidad, String jornada,
            Integer rut, String userType);
    List<ActaDeptoView> findDepto(Integer agno, Integer sem, Integer tipoCarrera, Integer especialidad, String jornada,
            Integer rut, String userType);
    List<ActaFacultadView> findFacultad(Integer agno, Integer sem, Integer tipoCarrera, Integer especialidad, String jornada,
            Integer rut, String userType);
}
