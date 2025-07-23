/*
 * @(#)MensajeDestinatarioPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.MensajeDestinatario;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface MensajeDestinatarioRepository extends CrudGenericDAO<MensajeDestinatario, Long> {

    void saveDest(Integer correl, Integer rut);
    
    /**
     * Method description
     *
     * @param rut
     * @return
     */
    Long countMsgs(Integer rut);    
    
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
    List<MensajeDestinatario> findReceivedWithLimits(Integer rut, Integer start, Integer length, String searchValue, String tipoOrder, String nombreDataColumnaActual);
    
    /**
     * Method description
     *
     * @param rut
     * @return
     */
    int countMsgsReceived(Integer rut);
    
    /**
     * Method description
     *
     * @param rut
     * @param searchValue
     * @return
     */
    int countMsgsReceivedFiltered(Integer rut, String searchValue);

    /**
     * Method description
     *
     * @param mensajeDestinatario
     */
    void setDeleteReceivedMessage(MensajeDestinatario mensajeDestinatario);

    /**
     * Method description
     *
     * @param mensajeDestinatario
     */
    void setReadMessage(MensajeDestinatario mensajeDestinatario);

    /**
     *
     * @param correl
     * @return
     */
    List<String> findSent(Integer correl);
}
