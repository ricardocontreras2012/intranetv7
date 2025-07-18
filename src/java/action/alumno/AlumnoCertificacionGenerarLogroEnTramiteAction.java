/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.alumno;

import service.alumno.AlumnoCertificacionGenerarLogroEnTramiteService;
import session.Manager;
import infrastructure.support.action.ActionValidationPosSupport;

/**
 *
 * @author Ricardo
 */
public class AlumnoCertificacionGenerarLogroEnTramiteAction extends ActionValidationPosSupport {

    private static final long serialVersionUID = 1L;
    private Integer tramite;
    private String  obs;
    private Integer correl;

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action(){          
        return new AlumnoCertificacionGenerarLogroEnTramiteService().service(this, getGenericSession(), Manager.getAlumnoSession(sesion), getPos(), tramite, obs, getKey());       
    }
    
    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return true;
    }

    public void setTramite(Integer tramite) {
        this.tramite = tramite;
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
