/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.teletrabajo;

import static service.teletrabajo.TeleTrabajoSaveActividadService.service;
import session.Manager;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author rcontreras
 */
public class TeleTrabajoSaveActividadAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private String fecha;
    private String descripcion;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return service(this, getGenericSession(), Manager.getTeleTrabajoSession(sesion), descripcion, fecha, getKey());
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
