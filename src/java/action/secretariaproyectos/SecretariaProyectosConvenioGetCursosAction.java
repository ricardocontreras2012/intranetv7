/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.secretariaproyectos;

import service.secretariaproyectos.SecretariaProyectosConvenioGetCursosService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo
 */
public class SecretariaProyectosConvenioGetCursosAction extends ActionCommonSupport {
    private static final long serialVersionUID = 1L;
    private Integer rut;
    private Integer agno;
    private Integer sem;
    private String proyecto;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new SecretariaProyectosConvenioGetCursosService().service(getGenericSession(), rut, agno, sem, proyecto, getKey());
    }

    public void setRut(Integer rut) {
        this.rut = rut;
    }

    public void setAgno(Integer agno) {
        this.agno = agno;
    }

    public void setSem(Integer sem) {
        this.sem = sem;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }   
}

