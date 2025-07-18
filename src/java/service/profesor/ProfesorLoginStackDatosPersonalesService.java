/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.profesor;

import com.opensymphony.xwork2.Action;
import domain.model.Profesor;
import session.GenericSession;
import session.ProfesorSession;

/**
 *
 * @author Usach
 */
public class ProfesorLoginStackDatosPersonalesService {
     public String service(GenericSession genericSession, ProfesorSession ps, String key)
            throws Exception {

        Profesor prof = ps.getProfesor();
        if (prof.getProfUpdated() == null) {
            return Action.SUCCESS;
        } else {
            return "stack";            
        }
    }
}
