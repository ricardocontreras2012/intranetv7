/*
 * @(#)AluCarFunctionsPersistenceView.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.AluCar;
import domain.model.AluCarFunctionsView;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface AluCarFunctionsPersistenceView extends CrudGenericDAO<AluCarFunctionsView, Long> {

    /**
     * Method description
     *
     * @param aluCar
     * @return
     */
    AluCarFunctionsView find(AluCar aluCar);
}
