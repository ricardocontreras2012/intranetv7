/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.alumno;

import service.alumno.AlumnoCertificacionGenerarRankEgresadoService;
import session.Manager;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo
 */
public class AlumnoCertificacionGenerarRankEgresadoAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer tramite;
    private String  obs;
    private Integer correl;
    private Integer rank;

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action(){          
        return new AlumnoCertificacionGenerarRankEgresadoService().service(this, getGenericSession(), Manager.getAlumnoSession(sesion), tramite, obs, rank, getKey());       
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

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}

