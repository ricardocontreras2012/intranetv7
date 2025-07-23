/*
 * @(#)PregEnctaPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.EncuestaDocente;
import domain.model.PregEncta;
import java.util.List;


/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface PregEnctaRepository extends CrudGenericDAO<PregEncta, Long> {
    List<PregEncta> find(EncuestaDocente encuesta);
}
