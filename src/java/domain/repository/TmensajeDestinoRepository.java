/*
 * @(#)TmensajeDestinoPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.TmensajeDestino;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface TmensajeDestinoRepository extends CrudGenericDAO<TmensajeDestino, Long> {

    /**
     * Method description
     *
     * @param user
     * @return
     */
    List<TmensajeDestino> find(String user);

    /**
     * Method description
     *
     * @param user
     * @return
     */
    List<TmensajeDestino> findSinCursos(String user);

    /**
     * Method description
     *
     * @param user
     * @return
     */
    List<TmensajeDestino> findOtros(String user);
}
