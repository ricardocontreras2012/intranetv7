/*
 * @(#)ParamArchivosWebPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.ParamArchivosWebRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.ParamArchivosWeb;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class ParamArchivosWebPersistenceImpl extends CrudAbstractDAO<ParamArchivosWeb, Long>
        implements ParamArchivosWebRepository {
}
