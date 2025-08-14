/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import service.inscripcion.CommonInscripcionAdmRemoveNominaIzqService;
import session.Manager;
import infrastructure.support.action.ActionParameterAwareSupport;

/**
 *
 * @author Felipe
 */
public class CommonInscripcionAdmRemoveNominaIzqAction extends ActionParameterAwareSupport {
    
    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new CommonInscripcionAdmRemoveNominaIzqService().service(getGenericSession(), Manager.getJefeCarreraSession(sesion) , getMapParameters(), getKey());
    }
}
