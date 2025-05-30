/*
 * @(#)AsignaturaPersistence.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.AluCar;
import domain.model.Asignatura;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface AsignaturaPersistence extends CrudGenericDAO<Asignatura, Long> {
    List<Asignatura> find(String tipo);
    Asignatura find(AluCar aluCar, Integer tipoSolicitud);
    List<Asignatura> findFI(Integer rut, String userType);
}
