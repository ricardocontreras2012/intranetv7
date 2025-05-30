/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.vicedecano;

import static service.vicedecano.ViceDecanoSolicitudSaveResolucionService.service;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Administrador
 */
public class ViceDecanoSolicitudSaveResolucionAction  extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private String resolucion;
    private String respuesta;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {        
        return service(getGenericSession(), getKey(), resolucion, respuesta);
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }    
}
