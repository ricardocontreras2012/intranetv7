/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.Unidad;
import java.util.List;

/**
 *
 * @author Ricardo
 */
public interface UnidadPersistence extends CrudGenericDAO<Unidad, Long> {

    Unidad find(Integer unidad);
    Unidad findById(Integer uniCod);
    List<Unidad> findFacultad(Integer rut, String type);
    List<Unidad> findDeptos(Integer rut, String type);
    List<Unidad> findCarreras(Integer rut, String type);
}

