/*
 * @(#)CursoProfesorPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.CursoId;
import domain.model.DocenteHorario;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface DocenteHorarioRepository extends CrudGenericDAO<DocenteHorario, Long> {
    List<DocenteHorario> findDocente(CursoId cursoId, String tipo);
    void addDocente(CursoId id, Integer rut, String horario, String tipo);
    void removeDocente(CursoId id, Integer rut, String horario, String tipo);
}
