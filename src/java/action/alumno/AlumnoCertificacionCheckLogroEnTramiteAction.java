/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.alumno;

import static service.alumno.AlumnoCertificacionCheckLogroEnTramiteService.service;
import session.Manager;
import infrastructure.support.action.ActionValidationPosSupport;

/**
 *
 * @author Ricardo
 */
public class AlumnoCertificacionCheckLogroEnTramiteAction extends ActionValidationPosSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action() {   
        return service(Manager.getAlumnoSession(sesion), getPos());
    }

    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        /*return isValidPos(getPos(),
                getGenericSession().getWorkSession(getKey()).getCalificacionRequisitoAdicionalLogroxInscribirList());
         */
        return true;
    }
}
