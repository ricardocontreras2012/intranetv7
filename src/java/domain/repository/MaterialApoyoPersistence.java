/*
 * @(#)MaterialApoyoPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.CursoId;
import domain.model.MaterialApoyo;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface MaterialApoyoPersistence extends CrudGenericDAO<MaterialApoyo, Long> {

    /**
     * Method description
     *
     * @param id
     * @param rut
     * @param tipoUsuario
     * @param tipoMaterial
     * @return
     */
    List<MaterialApoyo> find(CursoId id, Integer rut, String tipoUsuario, String tipoMaterial);

    /**
     * Method description
     *
     * @param id
     * @return
     */
    List<MaterialApoyo> findOtros(CursoId id);
}
