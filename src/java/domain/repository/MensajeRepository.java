/*
 * @(#)MensajePersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.Mensaje;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface MensajeRepository extends CrudGenericDAO<Mensaje, Long> {

    /**
     *
     * @param msg
     */
    void saveMsg(Mensaje msg);
   

    /**
     * Method description
     *
     * @param key
     * @param correl
     * @return
     */
    Mensaje find(String key, Integer correl);
    
    /**
     * Method description
     *
     * @param rut
     * @param start
     * @param length
     * @param searchValue
     * @param tipoOrder
     * @param nombreDataColumnaActual
     * @return
     */
    List<Mensaje> find(Integer rut, Integer start, Integer length, String searchValue, String tipoOrder, String nombreDataColumnaActual);
    
    /**
     * Method description
     *
     * @param rut
     * @return
     */
    int countMsgs(Integer rut);
    
    /**
     * Method description
     *
     * @param rut
     * @param searchValue
     * @return
     */
    int countMsgsFiltered(Integer rut, String searchValue);

    /**
     * Method description
     *
     * @param mensaje
     */
    void setDeleteSentMessage(Mensaje mensaje);
}
