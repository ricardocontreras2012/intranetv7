/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.LogSolicitud;
import java.util.List;

/**
 *
 * @author Ricardo Contreras S.
 */
public interface LogSolicitudRepository extends CrudGenericDAO<LogSolicitud, Long> {

    /**
     * Method description
     *
     * @param solicitud
     * @return
     */
    List<LogSolicitud> find(Integer solicitud);
}



