/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.persistence;

import domain.repository.ConvalidacionComisionPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.ConvalidacionComision;

/**
 *
 * @author rcontreras
 */
public class ConvalidacionComisionPersistenceImpl extends CrudAbstractDAO<ConvalidacionComision, Long> implements ConvalidacionComisionPersistence {
}
