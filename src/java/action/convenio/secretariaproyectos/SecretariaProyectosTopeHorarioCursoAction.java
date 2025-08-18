/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.convenio.secretariaproyectos;

import service.convenio.secretariaproyectos.SecretariaProyectosTopeHorarioCursoService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo
 */
public class SecretariaProyectosTopeHorarioCursoAction extends ActionCommonSupport {
    private static final long serialVersionUID = 1L;

    private Integer rut;
    private Integer curso;
    private String fechaInicio;
    private String fechaTermino;


    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new SecretariaProyectosTopeHorarioCursoService().service(getGenericSession(), rut, curso, fechaInicio, fechaTermino, getKey());
    }

    public void setRut(Integer rut) {
        this.rut = rut;
    }

    public void setCurso(Integer curso) {
        this.curso = curso;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaTermino(String fechaTermino) {
        this.fechaTermino = fechaTermino;
    }
}
