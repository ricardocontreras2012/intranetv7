/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.alumno;

import infrastructure.support.action.ActionValidationPosSupport;
import service.solicitud.expediente.alumno.AlumnoNewSolicitudService;

/**
 *
 * @author Ricardo
 */
public class AlumnoSolicitudExpedienteNewAction extends ActionValidationPosSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action() {
        return new AlumnoNewSolicitudService().service(this, getGenericSession(), getPos(), getKey());
    }

    /**
     * Method description
     *
     * @return
     */
    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(getPos(), getGenericSession().getWorkSession(getKey()).getExpedienteLogroList());
    }
}
