/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import service.solicitud.SaveResolucionService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo Contreras S.
 */
public class CommonSolicitudSaveResolucionAction extends ActionCommonSupport {

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
        return new SaveResolucionService().service(getGenericSession(), getKey(), resolucion, respuesta);
    }

    /**
     *
     * @return
     */
    public String getResolucion() {
        return resolucion;
    }

    /**
     *
     * @param resolucion
     */
    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    /**
     *
     * @return
     */
    public String getRespuesta() {
        return respuesta;
    }

    /**
     *
     * @param respuesta
     */
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
