/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.repository;

import domain.model.EstadoDocExp;
import domain.model.ExpedienteLogroId;
import java.util.List;

/**
 *
 * @author Ricardo
 */
public interface EstadoDocExpRepository {
    
    List<EstadoDocExp> find(ExpedienteLogroId id);
    void updateFile(Integer rut, Integer cod_car, Integer agno_ing, Integer sem_ing, Integer correl_logro, Integer tdoc, String file);
}
