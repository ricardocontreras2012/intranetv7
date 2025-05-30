/*
 * @(#)LogCertificacionPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.LogCertificacion;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface LogCertificacionPersistence extends CrudGenericDAO<LogCertificacion, Long> {

    /**
     * Method description
     *
     * @param folio
     * @param verificador
     * @return
     */
    LogCertificacion find(Integer folio, String verificador);
}
