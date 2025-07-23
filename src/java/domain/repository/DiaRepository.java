/*
 * @(#)DiaRepository.java
 *
 * Copyright (c) Copyright (c) 2025 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.Dia;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface DiaRepository extends CrudGenericDAO<Dia, Long> {
    List<Dia> findClases();   
}
