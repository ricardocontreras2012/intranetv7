/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.EstadoSolicitud;
import java.util.List;

/**
 *
 * @author Ricardo Contreras S.
 */
public interface EstadoSolicitudPersistence extends CrudGenericDAO<EstadoSolicitud, Long> {

    /**
     *
     * @return
     */
    List<EstadoSolicitud> find();

}
