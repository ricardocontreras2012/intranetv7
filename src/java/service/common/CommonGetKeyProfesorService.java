/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import domain.model.Profesor;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.common.CommonRandomUtil;

/**
 *
 * @author Ricardo
 */
public class CommonGetKeyProfesorService {

    public String service(GenericSession genericSession) { 
        String keyProf = CommonRandomUtil.getKeySession();
        WorkSession wsProfesor = new WorkSession(genericSession.getUserType());

        Profesor profesor = genericSession.getProfesorSession().getProfesor();
        genericSession.getSessionMap().put(keyProf, wsProfesor);
        wsProfesor.setProfesor(profesor);
        
        wsProfesor.setCursoList(profesor.getCarga());
        wsProfesor.setProfesor(profesor);
                
        return keyProf;
    }
}
