/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.secretariaproyectos;

import service.convenio.secretariaproyectos.SecretariaProyectosConvenioTopeHorarioService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo
 */
public class SecretariaProyectosConvenioTopeHorarioAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer rut;
    private String dia;
    private String fechaInicio;
    private String fechaTermino;
    private String horaInicio;
    private String horaTermino;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new SecretariaProyectosConvenioTopeHorarioService().service(getGenericSession(), rut, dia, fechaInicio, fechaTermino, horaInicio, horaTermino, getKey());
    }

    public void setRut(Integer rut) {
        this.rut = rut;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaTermino(String fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraTermino(String horaTermino) {
        this.horaTermino = horaTermino;
    }    
}
