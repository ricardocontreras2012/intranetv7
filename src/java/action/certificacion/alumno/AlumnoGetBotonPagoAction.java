/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.certificacion.alumno;

import service.certificacion.alumno.AlumnoGetBotonPagoService;
import session.Manager;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Administrador
 */
public class AlumnoGetBotonPagoAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private String url;

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action(){
        url = new AlumnoGetBotonPagoService().service(Manager.getAlumnoSession(sesion));
        return SUCCESS;
    }

    public String getUrl() {
        return url;
    }    
}