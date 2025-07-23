/*
 * @(#)HorarioPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.CursoId;
import domain.model.Horario;
import domain.model.Sala;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface HorarioRepository extends CrudGenericDAO<Horario, Long> {
    List<Horario> findSalas(Sala sala, Integer agno, Integer sem);
    List<Horario> hayTope(Horario horario);
    List<Horario> getHorario(CursoId id);
    List<Horario> findxTipo(CursoId id, Character tipo);
    List<Horario> getHorarioByAgnoSem(Integer agno, Integer sem, String dia);
    List<Horario> getHorarioFAEByAgnoSem(Integer agno, Integer sem, String dia);
    String getHorarioCoumn(CursoId id);
}
