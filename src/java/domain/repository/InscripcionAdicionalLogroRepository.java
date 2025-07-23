/*
 * @(#)InscripcionAdicionalLogroPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.InscripcionAdicionalLogro;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface InscripcionAdicionalLogroRepository extends CrudGenericDAO<InscripcionAdicionalLogro, Long> {

    /**
     * Method description
     *
     * @param agnoInscripcion
     * @param semInscripcion
     * @param trequisito
     * @return
     */
    List<InscripcionAdicionalLogro> find(Integer agnoInscripcion, Integer semInscripcion,
            Integer trequisito);

    /**
     * Method description
     *
     * @param trequisito
     * @return
     */
    List<InscripcionAdicionalLogro> findxCalificar(Integer trequisito);
}
