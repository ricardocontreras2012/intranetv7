/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.certificacion.alumno;

import service.certificacion.alumno.AlumnoCheckNoImpedimientosService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Administrador
 */
public class AlumnoCheckNoImpedimentosAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action(){
        return new AlumnoCheckNoImpedimientosService().service(getGenericSession(), getKey());
    }
}
