/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.certificacion.alumno;

import service.certificacion.alumno.AlumnoCheckLogroEnTramiteService;
import session.Manager;
import infrastructure.support.action.ActionValidationPosSupport;

/**
 *
 * @author Ricardo
 */
public class AlumnoCheckLogroEnTramiteAction extends ActionValidationPosSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action() {   
        return new AlumnoCheckLogroEnTramiteService().service(Manager.getAlumnoSession(sesion), getPos());
    }

    @Override
    public boolean isValidParam() throws IllegalArgumentException {        
        return true;
    }
}
