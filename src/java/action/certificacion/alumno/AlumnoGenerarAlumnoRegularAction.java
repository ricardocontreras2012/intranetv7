/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.certificacion.alumno;

import service.certificacion.alumno.AlumnoGenerarAlumnoRegularService;
import session.Manager;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Administrador
 */
public class AlumnoGenerarAlumnoRegularAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer tramite;
    private Integer periodo;
    private String  obs;
    private Integer correl;

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action(){                   
        return new AlumnoGenerarAlumnoRegularService().service(this, getGenericSession(), Manager.getAlumnoSession(sesion), tramite, periodo, obs, getKey());       
    }

    public void setTramite(Integer tramite) {
        this.tramite = tramite;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
    }

    public void setObs(String obs) {
        this.obs = obs;
    } 

    public Integer getCorrel() {
        return correl;
    }
    
    public void setCorrel(Integer correl) {
        this.correl = correl;
    } 
}
