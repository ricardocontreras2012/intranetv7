/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.alumno;

import infrastructure.support.action.common.ActionCommonSupport;
import service.alumno.AlumnoSolicitudExpedienteLogradosService;

/**
 *
 * @author Ricardo
 */
public class AlumnoSolicitudExpedienteLogradosAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    /**/
    private boolean fechaSolLogro;

    public boolean getFechaSolLogro() {
        return fechaSolLogro;
    }

    public void setFechaSolLogro(boolean fechaSolLogro) {
        this.fechaSolLogro = fechaSolLogro;
    }
    /**/
    @Override
    public String action() {
        return new AlumnoSolicitudExpedienteLogradosService().service( getGenericSession(), getKey(), this);
    }    
}
