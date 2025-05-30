/*
 * @(#)MensajeAttachPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.MensajeAttach;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface MensajeAttachPersistence extends CrudGenericDAO<MensajeAttach, Long> {
    
    void saveAttach(Integer msg, Integer correl, String file);
    /**
     * Method description
     *
     * @param correl
     * @return
     */
    List<MensajeAttach> find(Integer correl);

    /**
     * Method description
     *
     *
     * @param correl
     * @param file
     * @param key
     *
     * @return
     */
    MensajeAttach find(Integer correl, String file, String key);

    /**
     * Method description
     *
     *
     * @param correl
     * @param file
     *
     * @return
     */
    MensajeAttach find(Integer correl, Integer file);
}
