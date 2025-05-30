/*
 * @(#)ReservaSalaPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.ReservaSala;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface ReservaSalaPersistence extends CrudGenericDAO<ReservaSala, Long> {

    /**
     * Method description
     *
     * @param reserva
     * @return
     */
    ReservaSala find(Integer reserva);
    void reservarSala(String sala, String dia, Integer modulo, String inicio, String termino, String motivo, Integer rut);
    List<ReservaSala> findReservas(Integer rut);
    void remove(Integer correl);
}
