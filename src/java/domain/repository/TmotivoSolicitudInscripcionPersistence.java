/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.TmotivoSolicitudInscripcion;
import java.util.List;

/**
 *
 * @author Ricardo
 */
public interface TmotivoSolicitudInscripcionPersistence extends CrudGenericDAO<TmotivoSolicitudInscripcion, Long> {

    /**
     * Method description
     *
     * @return
     */
    List<TmotivoSolicitudInscripcion> find();
}

