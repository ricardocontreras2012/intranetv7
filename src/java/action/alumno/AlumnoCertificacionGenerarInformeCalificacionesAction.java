
package action.alumno;

import service.certificacion.alumno.AlumnoCertificacionGenerarInformeCalificacionesService;
import session.Manager;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo
 */
public class AlumnoCertificacionGenerarInformeCalificacionesAction extends ActionCommonSupport {

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
        return new AlumnoCertificacionGenerarInformeCalificacionesService().service(this, getGenericSession(), Manager.getAlumnoSession(sesion), tramite, obs, getKey());       
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


