/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.SolicitudCertificadoCarrito;
import java.util.List;

/**
 *
 * @author Ricardo
 */
public interface SolicitudCertificadoCarritoRepository extends CrudGenericDAO<SolicitudCertificadoCarrito, Long> {
    List<SolicitudCertificadoCarrito> find(String userType, Integer rut);
}
