/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.Funcionario;
import java.util.List;

/**
 *
 * @author Ricardo
 */
public interface FuncionarioPersistence extends CrudGenericDAO<Funcionario, Long> {

    /**
     * Method description
     *
     * @param apellido
     * @return
     */
    List<Funcionario> find(String apellido);
    Funcionario find(Integer rut);
    void creaFuncionario(Integer rut);
    void modify(Integer rut, String direccion);
}
