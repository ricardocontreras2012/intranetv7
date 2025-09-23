/*
 * @(#)DerechoRepository.java
 *
 * Copyright (c) Copyright (c) 2025 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.AluCar;
import domain.model.AluCarId;
import domain.model.Derecho;
import java.util.List;
import infrastructure.support.DerechoCoordinadorSupport;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface DerechoRepository extends CrudGenericDAO<Derecho, Long> {
    String getDerechosJson(AluCarId id);
    List<DerechoCoordinadorSupport> getDerechoCoordinador(AluCar aluCar, Integer rut, String userType);
    List<DerechoCoordinadorSupport> getDerechoFI(AluCar aluCar, Integer rut, String userType);
    List<DerechoCoordinadorSupport> getDerechoCoordinadorLibre(AluCar aluCar, Integer rut);
}
