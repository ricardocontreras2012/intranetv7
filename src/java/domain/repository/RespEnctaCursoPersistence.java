/*
 * @(#)RespEnctaCursoPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.CursoId;
import java.util.List;
import domain.model.RespEnctaCursoView;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface RespEnctaCursoPersistence extends CrudGenericDAO<RespEnctaCursoView, Long> {

    /**
     * Method description
     *
     * @param cursoId
     * @param rutProf
     * @param tipo
     * @return
     */
    List<RespEnctaCursoView> find(CursoId cursoId, Integer rutProf, String tipo);
}
