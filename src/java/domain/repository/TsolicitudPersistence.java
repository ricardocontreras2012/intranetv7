/*
 * @(#)TsolicitudPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.AluCar;
import domain.model.Tsolicitud;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface TsolicitudPersistence extends CrudGenericDAO<Tsolicitud, Long> {

    List<Tsolicitud> find(AluCar aluCar);
}
