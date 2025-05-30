/*
 * @(#)SolicitudAttachPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.Solicitud;
import domain.model.SolicitudAttach;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface SolicitudAttachPersistence extends CrudGenericDAO<SolicitudAttach, Long> {

    /**
     * Method description
     *
     * @param solicitud
     * @return
     */
    List<SolicitudAttach> find(Solicitud solicitud);
    void addAttach(Integer solicitud, Integer attach, String archivo, Integer tipo);
    void deleteAttach(Integer solicitud, Integer attach);
}
