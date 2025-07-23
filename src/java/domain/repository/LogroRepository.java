/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.Logro;
import java.util.List;

/**
 *
 * @author rcontreras
 */
public interface LogroRepository extends CrudGenericDAO<Logro, Long> {
    List<Logro> find();
    Logro find(Integer logro);
}
