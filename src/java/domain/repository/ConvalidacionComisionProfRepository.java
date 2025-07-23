/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.ConvalidacionComisionProf;
import java.util.List;

/**
 *
 * @author rcontreras
 */

public interface ConvalidacionComisionProfRepository extends CrudGenericDAO<ConvalidacionComisionProf, Long> {
    List<ConvalidacionComisionProf> find(Integer correl);
}
