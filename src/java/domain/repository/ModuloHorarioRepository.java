/*
 * @(#)ModuloHorarioPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.ModuloHorario;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface ModuloHorarioRepository extends CrudGenericDAO<ModuloHorario, Long> {
    List<ModuloHorario> findDocencia(Integer agno, Integer sem);
    List<ModuloHorario> findAll(String inicio, String termino);
    ModuloHorario find(Integer agno, Integer sem, Integer modulo);
}
