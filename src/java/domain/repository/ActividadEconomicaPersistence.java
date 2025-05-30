/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.ActividadEconomica;
import java.util.List;

/**
 *
 * @author Virtual
 */
public interface ActividadEconomicaPersistence extends CrudGenericDAO<ActividadEconomica, Long> {
        /**
     * Method description
     *
     * @return
     */
    List<ActividadEconomica> find();
}
