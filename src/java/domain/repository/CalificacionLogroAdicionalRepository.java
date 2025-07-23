/*
 * @(#)CalificacionRequisitoAdicionalLogroPersistence.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.AluCar;
import domain.model.CalificacionLogroAdicional;
import java.util.List;
import infrastructure.support.CalificacionCertificacionSupport;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface CalificacionLogroAdicionalRepository extends CrudGenericDAO<CalificacionLogroAdicional, Long> {

    /**
     * Method description
     *
     * @param aluCar
     * @return
     */
    List<CalificacionLogroAdicional> find(AluCar aluCar);

    /**
     * Method description
     *
     * @param aluCar
     * @return
     */
    List<CalificacionCertificacionSupport> findAprobadas(AluCar aluCar);
    
    /**
     * Method description
     *
     * @param aluCar
     * @param trequisito
     * @return
     */
    boolean puedeInscribirAdicional(AluCar aluCar, Integer trequisito);
}
