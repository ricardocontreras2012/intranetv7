/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.secretariaproyectos;

import infrastructure.support.action.common.ActionCommonSupport;
import static service.secretariaproyectos.SecretariaProyectosConvenioGetProyectoService.service;
import session.Manager;

/**
 *
 * @author Ricardo
 */
public class SecretariaProyectosConvenioGetProyectoAction  extends ActionCommonSupport {
    private static final long serialVersionUID = 1L;

    private String proyecto;


    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return service(getGenericSession(), Manager.getProyectoSession(sesion), proyecto, getKey());
    }   

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }        
}
