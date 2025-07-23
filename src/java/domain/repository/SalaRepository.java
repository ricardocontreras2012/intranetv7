/*
 * @(#)SalaPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.Sala;
import java.util.List;
import infrastructure.support.ReservaSalaSupport;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface SalaRepository extends CrudGenericDAO<Sala, Long> {  
    List<Sala> findPropias(Integer rut) throws Exception;
    List<ReservaSalaSupport> getHorario(Sala sala, String inicio, String termino);
}
