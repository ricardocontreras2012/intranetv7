/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.AluCarId;
import domain.model.ConvalidacionSolicitud;
import java.util.List;

/**
 *
 * @author rcontreras
 */
public interface ConvalidacionSolicitudPersistence extends CrudGenericDAO<ConvalidacionSolicitud, Long> {
    List<ConvalidacionSolicitud> find(AluCarId id);
    void setEstado(Integer correl, String estado);
}