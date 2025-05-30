/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import static service.common.CommonPracticaGetSolicitudesxEstadoService.service;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author rcontreras
 */
public class CommonPracticaGetSolicitudesxEstadoAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer autoridad;
    private Integer estado;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return service(getGenericSession(), getKey(), autoridad, estado);
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getAutoridad() {
        return autoridad;
    }

    public void setAutoridad(Integer autoridad) {
        this.autoridad = autoridad;
    }
}
