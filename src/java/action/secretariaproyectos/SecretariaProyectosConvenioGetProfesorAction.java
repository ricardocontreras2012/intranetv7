/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.secretariaproyectos;

import service.convenio.secretariaproyectos.SecretariaProyectosConvenioGetProfesorService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo
 */
public class SecretariaProyectosConvenioGetProfesorAction extends ActionCommonSupport {
    private static final long serialVersionUID = 1L;

    private Integer rut;


    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new SecretariaProyectosConvenioGetProfesorService().service(getGenericSession(), rut, getKey());
    }

    public void setRut(Integer rut) {
        this.rut = rut;
    }
}

