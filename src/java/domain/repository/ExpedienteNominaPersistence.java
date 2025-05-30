/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.ExpedienteNomina;

/**
 *
 * @author rcontreras
 */
public interface ExpedienteNominaPersistence extends CrudGenericDAO<ExpedienteNomina, Long> {

    void saveNomina(ExpedienteNomina nomina);

}
