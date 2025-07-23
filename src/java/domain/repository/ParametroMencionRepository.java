/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.ParametroMencion;
import java.util.Date;

/**
 *
 * @author Usach
 */
public interface ParametroMencionRepository extends CrudGenericDAO<ParametroMencion, Long> {
    ParametroMencion getParameter(Integer carrera, Integer mencion);
    void updateParametroMencion(Integer pmenCodCar, Integer pmenCodMen, 
                                             Integer pmenAgnoAct, Integer pmenSemAct, 
                                             Integer pmenAgnoCal, Integer pmenSemCal, 
                                             Integer pmenAgnoEnc, Integer pmenSemEnc, 
                                             Integer pmenAgnoIns, Integer pmenSemIns, 
                                             Date pmenInsPostInicio, Date pmenInsPostTermino, 
                                             Date pmenInsAdmTermino, Date pmenInsModTermino, 
                                             Date pmenInsEliminTermino, String pmenInsLock);    
}
