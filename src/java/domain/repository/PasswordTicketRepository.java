/*
 * @(#)PasswordTicketPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.PasswordTicket;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface PasswordTicketRepository extends CrudGenericDAO<PasswordTicket, Long> {

    /**
     * Method description
     *
     * @param rut
     */
    void deleteTickets(Integer rut);

    /**
     * Method description
     *
     * @param passwordTicket
     */
    void insertTicket(PasswordTicket passwordTicket);

    /**
     * Method description
     *
     * @param rut
     * @param key
     * @return
     */
    PasswordTicket find(Integer rut, String key);

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    String getKey(Integer rut);
}
