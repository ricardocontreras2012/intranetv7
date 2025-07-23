/*
 * @(#)MencionInfoIntranetProfesorPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import java.util.List;
import domain.model.MencionInfoIntranetProfesorView;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface MencionInfoIntranetProfesorRepository extends CrudGenericDAO<MencionInfoIntranetProfesorView, Long> {

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    List<MencionInfoIntranetProfesorView> find(Integer rut);
}
