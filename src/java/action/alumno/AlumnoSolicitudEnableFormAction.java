/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.alumno;

import static service.alumno.AlumnoSolicitudEnableFormService.service;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author rcontreras
 */
public class AlumnoSolicitudEnableFormAction  extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer tipo;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return service(getGenericSession(), getKey(), tipo);
    }
   
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
}

