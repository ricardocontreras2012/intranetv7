/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.registradorcurricular;

import static service.registradorcurricular.RegistradorCurricularParametrosxMencionGetService.service;
import session.Manager;
import infrastructure.support.action.ActionValidationPosSupport;

/**
 *
 * @author Usach
 */
public class RegistradorCurricularParametrosxMencionGetAction extends ActionValidationPosSupport {
       
    @Override
    public String action() throws Exception {
        return service(getGenericSession(), Manager.getRegistradorSession(sesion), getPos(), getKey());
    }
    
    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(getPos(), getGenericSession().getWorkSession(getKey()).getMencionList());   
    }
}
