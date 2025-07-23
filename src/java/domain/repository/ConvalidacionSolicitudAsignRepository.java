/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.AluCar;
import domain.model.ConvalidacionSolicitudAsign;
import java.util.List;

/**
 *
 * @author rcontreras
 */
public interface ConvalidacionSolicitudAsignRepository extends CrudGenericDAO<ConvalidacionSolicitudAsign, Long> {
    List<ConvalidacionSolicitudAsign> getPorConvalidar(AluCar aluCar);
    List<ConvalidacionSolicitudAsign> getPorConvalidar(AluCar aluCar, Integer solicitud);
}