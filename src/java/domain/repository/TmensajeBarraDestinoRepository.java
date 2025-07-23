/*
 * @(#)TmensajeBarraDestinoPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.TmensajeBarraDestino;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface TmensajeBarraDestinoRepository extends CrudGenericDAO<TmensajeBarraDestino, Long> {

    /**
     * Method description
     *
     * @param destino
     * @return
     */
    List<TmensajeBarraDestino> find(String destino);
}
